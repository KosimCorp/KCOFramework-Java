/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kosim.kcoframework.view;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import org.kosim.kcoframework.KHelper;
import org.kosim.kcoframework.component.KComponent;
import org.kosim.kcoframework.component.SingletonComponent;
import org.kosim.kcoframework.component.element.Element;

/**
 *
 * @author raz
 * @param <S>
 * @param <T>
 */
public abstract class KViewChain<S extends KViewChain<S, T>, T> extends KView<T> {
    private List<Consumer<T>> buildListener;
    private List<Consumer<T>> oneBuildListener;
    private List<Consumer<T>> stateListener;
    
    private Supplier<KComponent> customRoot;

    @Override
    protected void update() {
        if (stateListener != null) {
            if (getLastElement().getLastRoot() != null)
                stateListener.forEach(consumer -> consumer.accept(getLastElement().getLastRoot()));
        }
        
        super.update();
    }
    
    protected S waitForBuild(Consumer<T> consumer) {
        return waitForBuild(consumer, false);
    }
    
    protected S waitForBuild(Consumer<T> consumer, boolean isOneRun) {
        if (getLastElement().getLastRoot() == null) {
            List<Consumer<T>> listener = isOneRun ? oneBuildListener : buildListener;
            
            if (listener == null) {
                if (isOneRun)
                    listener = oneBuildListener = new ArrayList<>();
                else
                    listener = buildListener = new ArrayList<>();
            }
            
            listener.add(consumer);
        } else {
            consumer.accept(getLastElement().getLastRoot());
        }
        
        return (S) this;
    }
    
    public S custom(Supplier<T> customRoot) {
        this.customRoot = (Supplier<KComponent>)() -> new SingletonComponent(customRoot.get());
        
        return (S) this;
    }
    
    public S configure(BiConsumer<T, S> call) {
        return configure((element) -> call.accept(element, (S) this));
    }
    
    public S configure(Consumer<T> call) {
        return waitForBuild((element) -> call.accept(element));
    }
    
    public S state(KState state) {
        state.listen((runner) -> setState(runner));
        
        return (S) this;
    }
    
    public S state(BiConsumer<T, S> call) {
        return state((element) -> call.accept(element, (S) this));
    }
    
    public S state(Consumer<T> call) {
        if (stateListener == null) {
            stateListener = new ArrayList<>();
        }
        
        stateListener.add(call);
        
        return waitForBuild(call, true);
    }
    
    public S when(Supplier<Boolean> condition, Consumer<T> call) {
        return state(element -> {
            if (condition.get()) call.accept(element);
        });
    }
    
    public S when(Supplier<Boolean> condition, BiConsumer<T, S> call) {
        return state((element, builder) -> {
            if (condition.get()) call.accept(element, builder);
        });
    }

    @Override
    public Element<T> createElement() {
        return KHelper.tap(super.createElement(customRoot == null ? this::build : customRoot), lastElement -> {
            lastElement.setAfterRender(root -> {
                if (buildListener != null)
                    buildListener.forEach(consumer -> consumer.accept(root));
                
                if (oneBuildListener != null) {
                    oneBuildListener.forEach(consumer -> consumer.accept(root));
                    oneBuildListener.clear();
                    oneBuildListener = null;
                }
            });
        });
    }
}

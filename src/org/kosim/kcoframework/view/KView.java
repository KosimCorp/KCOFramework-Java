/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kosim.kcoframework.view;

import java.util.function.Consumer;
import java.util.function.Supplier;
import org.kosim.kcoframework.KHelper;
import org.kosim.kcoframework.component.element.Element;
import org.kosim.kcoframework.component.KComponent;

/**
 *
 * @author raz
 * @param <T>
 */
public abstract class KView<T> extends KComponent<T> {
    public abstract KComponent build();

    @Override
    public Element<T> createElement() {
        return createElement(this::build);
    }
    
    Element<T> createElement(Supplier<KComponent> builder) {
        var child = builder.get();
        
        addChildren(child);
        
        return KHelper.tap(child.createElement(), element -> setLastElement(element));
    }
    
    protected KState useState() {
        return new KState();
    }
    
    public static class KState {
        private KState() {}
        
        private Consumer<Runnable> listener;
        
        public void set(Runnable runner) {
            listener.accept(runner);
        }
        
        public void listen(Consumer<Runnable> listener) {
            this.listener = listener;
        }
    }
}

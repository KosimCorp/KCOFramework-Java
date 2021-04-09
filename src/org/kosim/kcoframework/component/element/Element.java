/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kosim.kcoframework.component.element;

import java.util.function.Consumer;
import org.kosim.kcoframework.KCOFrameworkException;
import org.kosim.kcoframework.component.KComponent;

/**
 *
 * @author raz
 * @param <T>
 */
public class Element<T> {
    private final KComponent<T> component;
    
    private Runnable beforeRender;
    private Consumer<T> afterRender;
    
    private T lastRoot;
    
    public Element(KComponent<T> component) {
        this.component = component;
    }

    public T render() {
        if (beforeRender != null) beforeRender.run();
        
        lastRoot = (T) component.getEngine().render(component);
        
        if (afterRender != null) afterRender.accept(lastRoot);
        
        return lastRoot;
    }

    public KComponent<T> getComponent() {
        return component;
    }

    public Runnable getBeforeRender() {
        return beforeRender;
    }

    public void setBeforeRender(Runnable beforeRender) {
        this.beforeRender = beforeRender;
    }

    public Consumer<T> getAfterRender() {
        return afterRender;
    }

    public void setAfterRender(Consumer<T> afterRender) {
        this.afterRender = afterRender;
    }

    public T getLastRoot() {
        return lastRoot;
    }

    protected void setLastRoot(T lastRoot) {
        this.lastRoot = lastRoot;
    }
    
    public static class Empty<T> extends Element<T> {
        public Empty() {
            super(null);
        }

        @Override
        public T render() {
            throw new KCOFrameworkException("Empty Element Cant Used");
        }
    }
}

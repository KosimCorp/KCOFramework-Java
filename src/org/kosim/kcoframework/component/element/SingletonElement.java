/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kosim.kcoframework.component.element;

import org.kosim.kcoframework.component.SingletonComponent;

/**
 *
 * @author raz
 * @param <T>
 */
public class SingletonElement<T> extends Element<T> {
    public SingletonElement(SingletonComponent<T> component) {
        super(component);
    }

    @Override
    public T render() {
        if (getBeforeRender() != null) getBeforeRender().run();
        
        var root = (T) getComponent().getRoot();
        setLastRoot(root);
        
        if (getAfterRender() != null) getAfterRender().accept(root);
        
        return root;
    }

    @Override
    public SingletonComponent<T> getComponent() {
        return (SingletonComponent<T>) super.getComponent();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kosim.kcoframework.component;

import org.kosim.kcoframework.KHelper;
import org.kosim.kcoframework.component.element.SingletonElement;
import org.kosim.kcoframework.component.element.Element;

/**
 *
 * @author raz
 * @param <T>
 */
public class SingletonComponent<T> extends KComponent<T> {
    private final T root;
    
    public SingletonComponent(T root) {
        this.root = root;
    }

    @Override
    public Element<T> createElement() {
        return KHelper.tap(new SingletonElement<>(this), element -> setLastElement(element));
    }
    
    public T getRoot() {
        return root;
    }
}

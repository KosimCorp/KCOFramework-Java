/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kosim.kcoframework.view;

import org.kosim.kcoframework.component.KComponent;
import org.kosim.kcoframework.component.SingletonComponent;

/**
 *
 * @author raz
 * @param <S>
 * @param <T>
 */
public class KCustomView<S extends KCustomView<S, T>, T> extends KViewChain<S, T> {
    private T root;
    
    public KCustomView(Class<T> rootClass) {}
    
    public KCustomView(T root) {
        this.root = root;
    }
    
    public S root(T root) {
        this.root = root;
        
        return (S) this;
    }

    @Override
    public KComponent build() {
        return new SingletonComponent(root);
    }
}

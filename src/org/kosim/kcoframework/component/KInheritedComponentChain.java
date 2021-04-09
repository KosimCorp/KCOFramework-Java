/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kosim.kcoframework.component;

import org.kosim.kcoframework.component.element.Element;

/**
 *
 * @author raz
 * @param <S>
 * @param <T>
 */
public class KInheritedComponentChain<S extends KInheritedComponentChain<S, T>, T> extends KInheritedComponent<T> {
    public S child(KComponent child) {
        setChild(child);
        
        return (S) this;
    }
}

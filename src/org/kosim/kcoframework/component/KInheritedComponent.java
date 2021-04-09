/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kosim.kcoframework.component;

import org.kosim.kcoframework.component.element.Element;
import org.kosim.kcoframework.component.element.SingletonElement;

/**
 *
 * @author raz
 * @param <T>
 */
public class KInheritedComponent<T> extends KComponent<T> {
    private KComponent child;
    
    public KInheritedComponent() {
        setInherited(true);
    }
    
    protected void setChild(KComponent child) {
        this.child = child;
    }

    @Override
    public Element<T> createElement() {
        var element = child.createElement();
        
        addChildren(child);
        
        return element;
    }
}

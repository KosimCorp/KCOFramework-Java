/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kosim.kcoframework.component;

import org.kosim.kcoframework.component.KComponent;

/**
 *
 * @author raz
 */
public class BuildContext {
    private final KComponent component;
    
    protected BuildContext(KComponent component) {
        this.component = component;
    }
    
    public <T extends KComponent> T inheritedParentAndGet(Class<T> componentClass) {
        if (component.getParent() != null) {
            if (component.getParent().getClass() == componentClass) {
                return (T) component.getParent();
            }
            
            return component.getParent().context.inheritedParentAndGet(componentClass);
        }
        
        return null;
    }
}

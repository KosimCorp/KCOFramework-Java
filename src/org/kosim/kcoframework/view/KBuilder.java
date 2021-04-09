/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kosim.kcoframework.view;

import java.util.function.Supplier;
import org.kosim.kcoframework.component.KComponent;
import org.kosim.kcoframework.component.SingletonComponent;
import org.kosim.kcoframework.event.KEvent;

/**
 *
 * @author raz
 */
public class KBuilder<S extends KBuilder<S, T>, T> extends KViewChain<S, T> {
    public static final String CHANGE_CHILD = "change-child";
    
    private Supplier<KComponent> child;

    @Override
    protected void onUpdate() {
        if (getLastElement().getLastRoot() != null) {
            KComponent newComponent = child.get();
            
            if (getLastElement().getComponent() != newComponent) {
                getEngine().dispatch(this, new KEvent(CHANGE_CHILD));
            }
        }
    }
    
    public S child(Supplier<KComponent> child) {
        this.child = child;
        
        return (S) this;
    }

    @Override
    public KComponent build() {
        clearChildren();
        
        return new SingletonComponent(renderChild(child.get()));
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kosim.kcoframework.component;

import org.kosim.kcoframework.component.element.ElementEngine;
import org.kosim.kcoframework.component.element.Element;
import java.util.HashSet;
import java.util.Set;
import org.kosim.kcoframework.KCOFramework;

/**
 *
 * @author raz
 * @param <T>
 */
public class KComponent<T> {
    protected final BuildContext context = new BuildContext(this);
    
    private KComponent parent;
    private ElementEngine engine;
    
    private Element<T> lastElement = new Element.Empty<>();
    
    private boolean inherited;
    
    private Set<KComponent> children;
    
    public void setState(Runnable runner) {
        if (runner != null) runner.run();
        
        update();
    }
    
    protected void update() {
        onUpdate();
        
        if (children != null)
            children.forEach(KComponent::update);
    }
    
    protected void onUpdate() {}

    void setParent(KComponent parent) {
        this.parent = parent;
    }
    
    KComponent getParent() {
        return parent;
    }

    protected Set<KComponent> getChildren() {
        return children;
    }
    
    public <T> T renderChild(KComponent<T> child) {
        addChildren(child);
        
        return KCOFramework.render(child, getEngine());
    }
    
    protected void addChildren(KComponent child) {
        if (children == null) {
            children = new HashSet<>();
        }
        
        if (isInherited())
            child.setParent(this);
        else
            child.setParent(getParent());
        
        child.setEngine(engine);
        
        children.add(child);
    }
    
    protected void clearChildren() {
        if (children != null)
            children.clear();
    }
    
    protected void removeChildren(KComponent child) {
        if (children != null)
            children.remove(child);
    }

    protected boolean isInherited() {
        return inherited;
    }

    protected void setInherited(boolean inherited) {
        this.inherited = inherited;
    }

    public ElementEngine getEngine() {
        return engine;
    }

    public void setEngine(ElementEngine engine) {
        this.engine = engine;
    }
    
    public Element<T> createElement() {
        return lastElement = new Element(this);
    }

    protected void setLastElement(Element<T> lastElement) {
        this.lastElement = lastElement;
    }

    public Element<T> getLastElement() {
        return lastElement;
    }
}

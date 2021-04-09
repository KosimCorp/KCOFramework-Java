/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kosim.kcoframework.component.element;

import org.kosim.kcoframework.event.KEvent;
import org.kosim.kcoframework.component.KComponent;

/**
 *
 * @author raz
 * @param <T>
 */
public interface ElementEngine<T extends KComponent> {
    public Object render(T component);
    public void dispatch(T component, KEvent event);
    public Object get(T component, KEvent event);
}

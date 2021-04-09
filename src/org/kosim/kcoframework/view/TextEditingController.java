/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kosim.kcoframework.view;

import java.util.List;
import org.kosim.kcoframework.component.KComponent;
import org.kosim.kcoframework.event.KEvent;

/**
 *
 * @author raz
 */
public class TextEditingController {
    public static final String SET_TEXT = "set-text";
    public static final String CLEAR_TEXT = "clear-text";
    public static final String GET_TEXT = "get-text";
    
    private KComponent component;
    
    private String cacheText;

    public void setText(String text) {
        if (component != null)
            component.getEngine().dispatch(component, new KEvent(SET_TEXT, text));
        else
            cacheText = text;
    }

    public String getText() {
        return component != null ? (String) component.getEngine().get(component, new KEvent(GET_TEXT)) : cacheText;
    }

    public void clear() {
        if (component != null)
            component.getEngine().dispatch(component, new KEvent(CLEAR_TEXT));
    }

    public void setComponent(KComponent component) {
        this.component = component;
        
        if (cacheText != null)
            setText(cacheText);
    }
}

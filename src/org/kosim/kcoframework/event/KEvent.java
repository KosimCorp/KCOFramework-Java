/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kosim.kcoframework.event;

/**
 *
 * @author raz
 */
public class KEvent {
    private final Object key;
    private final Object[] args;
    
    public KEvent(Object key, Object... args) {
        this.key = key;
        this.args = args;
    }

    public Object getKey() {
        return key;
    }

    public Object[] getArgs() {
        return args;
    }
    
    
}

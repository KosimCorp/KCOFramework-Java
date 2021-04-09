/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kosim.kcoframework;

import java.util.function.Consumer;

/**
 *
 * @author raz
 */
public class KHelper {
    public static <T> T tap(T object, Consumer<T> consumer) {
        consumer.accept(object);
        
        return object;
    }
}

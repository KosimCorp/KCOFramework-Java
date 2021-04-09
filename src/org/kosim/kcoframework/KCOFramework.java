/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kosim.kcoframework;

import org.kosim.kcoframework.component.KComponent;
import org.kosim.kcoframework.component.element.ElementEngine;

/**
 *
 * @author raz
 */
public class KCOFramework {
    private static ElementEngine defaultEngine;
    
    public static void setDefaultElementEngine(ElementEngine defaultEngine) {
        KCOFramework.defaultEngine = defaultEngine;
    }
    
    public static <T> T render(KComponent<T> component) {
        return render(component, defaultEngine);
    }
    
    public static <T> T render(KComponent<T> component, ElementEngine engine) {
        component.setEngine(engine);
        
        return component.createElement().render();
    }
}

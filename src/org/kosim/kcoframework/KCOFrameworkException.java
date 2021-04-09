/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kosim.kcoframework;

/**
 *
 * @author raz
 */
public class KCOFrameworkException extends RuntimeException {

    public KCOFrameworkException() {
    }

    public KCOFrameworkException(String message) {
        super(message);
    }

    public KCOFrameworkException(String message, Throwable cause) {
        super(message, cause);
    }

    public KCOFrameworkException(Throwable cause) {
        super(cause);
    }

    public KCOFrameworkException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}

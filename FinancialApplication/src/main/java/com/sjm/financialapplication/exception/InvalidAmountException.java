/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.exception;

/**
 *
 * @author Steve
 */
public class InvalidAmountException extends IllegalArgumentException {

    public InvalidAmountException() {
    }

    public InvalidAmountException(String message) {
        super(message);
    }

    public InvalidAmountException(Throwable cause) {
        super(cause);
    }

    public InvalidAmountException(String message, Throwable cause) {
        super(message, cause);
    }
}

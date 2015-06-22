package com.ser.com.ser.exception;

/**
 * Created by Bradley on 6/21/2015.
 */
public class AlgorithmNotFoundException extends Exception{
    public AlgorithmNotFoundException() {
        super();
    }

    public AlgorithmNotFoundException(String message) {
        super(message);
    }

    public AlgorithmNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlgorithmNotFoundException(Throwable cause) {
        super(cause);
    }
}
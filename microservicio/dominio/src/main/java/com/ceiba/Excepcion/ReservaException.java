package com.ceiba.Excepcion;

public class ReservaException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ReservaException(String message) {
        super(message);
    }
}

package com.cac.tpcacfinal.exceptions;

public class UserExistingExceptions extends RuntimeException{
    public UserExistingExceptions(String mensaje){
        super(mensaje);
    }
}

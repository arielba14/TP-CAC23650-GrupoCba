package com.cac.tpcacfinal.exceptions;

public class UserNotFoundExceptions extends RuntimeException{
    public UserNotFoundExceptions(String mensaje){
        super(mensaje);
    }
}

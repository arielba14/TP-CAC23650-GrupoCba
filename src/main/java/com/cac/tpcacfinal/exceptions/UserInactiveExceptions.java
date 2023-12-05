package com.cac.tpcacfinal.exceptions;

public class UserInactiveExceptions extends RuntimeException{
    public UserInactiveExceptions(String mensaje){
        super(mensaje);
    }
}

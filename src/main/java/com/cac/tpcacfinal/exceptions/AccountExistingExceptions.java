package com.cac.tpcacfinal.exceptions;

public class AccountExistingExceptions extends RuntimeException{
    public AccountExistingExceptions(String mensaje){
        super(mensaje);
    }
}

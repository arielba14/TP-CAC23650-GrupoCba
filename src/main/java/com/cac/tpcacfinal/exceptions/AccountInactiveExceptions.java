package com.cac.tpcacfinal.exceptions;

public class AccountInactiveExceptions extends RuntimeException{
    public AccountInactiveExceptions(String mensaje){
        super(mensaje);
    }
}

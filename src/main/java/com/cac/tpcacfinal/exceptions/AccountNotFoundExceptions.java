package com.cac.tpcacfinal.exceptions;

public class AccountNotFoundExceptions extends RuntimeException{
    public AccountNotFoundExceptions(String mensaje){
        super(mensaje);
    }
}

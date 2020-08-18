package com.br.sistemabancariospringboot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class SaqueLimiteException extends RuntimeException{

    public SaqueLimiteException(String message){
        super(message);
    }

    public SaqueLimiteException(){

    }


}

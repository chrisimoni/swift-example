package com.chrisreal.swiftexample.exceptions;

public class BadRequestException extends AbstractException {
    private static final long serialVersionUID = 1L;

    public BadRequestException(String code, String message){
        super(code,message);
    }
}

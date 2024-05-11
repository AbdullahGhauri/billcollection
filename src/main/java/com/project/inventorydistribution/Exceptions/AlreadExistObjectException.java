package com.project.inventorydistribution.Exceptions;

public class AlreadExistObjectException extends RuntimeException{

    private String message;

    public  AlreadExistObjectException(){

    }
    public  AlreadExistObjectException(String msg)
    {
        super(msg);
        this.message = msg;
    }
}

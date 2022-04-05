package com.transactions.flight.exception;

public class InsufficientAmountException extends  RuntimeException{

    public InsufficientAmountException(String msg){
        super(msg);
    }
}

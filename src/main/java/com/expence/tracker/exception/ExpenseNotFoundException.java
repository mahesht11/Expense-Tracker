package com.expence.tracker.exception;

public class ExpenseNotFoundException extends RuntimeException{

    public ExpenseNotFoundException(String message){
        super(message);
    }
}

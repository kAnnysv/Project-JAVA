package org.example.repositories;

public class NotSavedSubEntityException extends RuntimeException{
    public NotSavedSubEntityException(String message){
        super(message);
    }
}

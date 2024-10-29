package com.eLearning.util;

public class ResourceAlreadyExitsException extends RuntimeException{
    public ResourceAlreadyExitsException(String message) {
        super(message);
    }
}

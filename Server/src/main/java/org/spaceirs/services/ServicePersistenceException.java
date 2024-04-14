package org.spaceirs.services;

public class ServicePersistenceException extends Exception{
    public ServicePersistenceException(String message) {
        super(message);
    }

    public ServicePersistenceException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public synchronized Throwable fillInStackTrace(){
        return this;
    }
}

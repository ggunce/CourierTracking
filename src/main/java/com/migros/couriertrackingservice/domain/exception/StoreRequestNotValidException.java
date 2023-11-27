package com.migros.couriertrackingservice.domain.exception;

public class StoreRequestNotValidException extends RuntimeException {

    public StoreRequestNotValidException(String message) {
        super(message);
    }
    public StoreRequestNotValidException() {
    }
}

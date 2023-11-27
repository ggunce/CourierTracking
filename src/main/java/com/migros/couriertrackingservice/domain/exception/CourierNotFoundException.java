package com.migros.couriertrackingservice.domain.exception;

public class CourierNotFoundException extends RuntimeException {

    public CourierNotFoundException(String message) {
        super(message);
    }
    public CourierNotFoundException() {
    }
}

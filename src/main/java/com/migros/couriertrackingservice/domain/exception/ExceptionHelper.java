package com.migros.couriertrackingservice.domain.exception;

import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionHelper {

    @ExceptionHandler(value = { ValidationException.class })
    public ResponseEntity<Object> handleValidationException(ValidationException ex) {
        log.error("Validation Exception: {}",ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = CourierNotFoundException.class)
    public ResponseEntity<String> handleCourierNotFoundException(CourierNotFoundException courierNotFoundException) {
        log.error("Courier Not Found Exception: {}", courierNotFoundException.getMessage());
        return new ResponseEntity<>("Courier Not Found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> handleException(Exception exception) {
        log.error("Internal Server Error: {}", exception.getMessage());
        return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.error("Method argument not valid exception: {}", exception.getMessage());
        return new ResponseEntity<>("Method argument not valid exception".concat(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = StoreRequestNotValidException.class)
    public ResponseEntity<String> handleStoreRequestNotValidException(StoreRequestNotValidException storeRequestNotValidException) {
        log.error("Store Request Not Valid Exception: {}", storeRequestNotValidException.getMessage());
        return new ResponseEntity<>("Creating Store Request Is Not Valid", HttpStatus.NOT_FOUND);
    }

}

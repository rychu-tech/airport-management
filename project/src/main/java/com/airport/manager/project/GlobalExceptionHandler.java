package com.airport.manager.project;

import com.airport.manager.project.features.carrier.exceptions.CarrierNameExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CarrierNameExistsException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CarrierNameExistsException ex) {
        return ResponseEntity.unprocessableEntity().body(new ErrorResponse("Carrier with such name exists!", 422));
    }
}
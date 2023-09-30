package com.airport.manager.project;

import com.airport.manager.project.features.airplane.exceptions.AirplaneSeatsNumberInvalidException;
import com.airport.manager.project.features.airplane.exceptions.AirplaneStatusNotFoundException;
import com.airport.manager.project.features.carrier.exceptions.CarrierNameExistsException;
import com.airport.manager.project.features.carrier.exceptions.CarrierNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CarrierNameExistsException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CarrierNameExistsException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(CarrierNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CarrierNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AirplaneStatusNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(AirplaneStatusNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AirplaneSeatsNumberInvalidException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(AirplaneSeatsNumberInvalidException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
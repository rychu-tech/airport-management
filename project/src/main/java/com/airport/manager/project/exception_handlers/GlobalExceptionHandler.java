package com.airport.manager.project.exception_handlers;

import com.airport.manager.project.exception_handlers.ErrorResponse;
import com.airport.manager.project.features.airplane.exceptions.AirplaneSeatsNumberInvalidException;
import com.airport.manager.project.features.airplane.exceptions.AirplaneStatusNotFoundException;
import com.airport.manager.project.features.carrier.exceptions.CarrierNameExistsException;
import com.airport.manager.project.features.carrier.exceptions.CarrierNotActiveException;
import com.airport.manager.project.features.carrier.exceptions.CarrierNotFoundException;
import com.airport.manager.project.features.destination.exceptions.DestinationNameExistsException;
import com.airport.manager.project.features.destination.exceptions.DestinationNotActiveException;
import com.airport.manager.project.features.destination.exceptions.DestinationNotFoundException;
import com.airport.manager.project.features.flight.exceptions.FlightCodeExistsException;
import com.airport.manager.project.features.flight.exceptions.FlightWrongDataException;
import com.airport.manager.project.features.gate.exceptions.GateNameExistsException;
import com.airport.manager.project.features.gate.exceptions.GateNotActiveException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            CarrierNameExistsException.class,
            CarrierNotFoundException.class,
            AirplaneStatusNotFoundException.class,
            AirplaneSeatsNumberInvalidException.class,
            DestinationNotFoundException.class,
            DestinationNameExistsException.class,
            GateNameExistsException.class,
            GateNotActiveException.class,
            DestinationNotActiveException.class,
            FlightCodeExistsException.class,
            CarrierNotActiveException.class,
            FlightWrongDataException.class
    })
    public ResponseEntity<ErrorResponse> handleCustomException(Exception ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // Default status

        if (ex instanceof CarrierNameExistsException || ex instanceof DestinationNameExistsException
                || ex instanceof GateNameExistsException || ex instanceof DestinationNotActiveException
                || ex instanceof FlightCodeExistsException || ex instanceof CarrierNotActiveException) {
            status = HttpStatus.BAD_REQUEST;
        } else if (ex instanceof CarrierNotFoundException || ex instanceof AirplaneStatusNotFoundException
                || ex instanceof DestinationNotFoundException) {
            status = HttpStatus.NOT_FOUND;
        } else if (ex instanceof AirplaneSeatsNumberInvalidException || ex instanceof GateNotActiveException) {
            status = HttpStatus.UNPROCESSABLE_ENTITY;
        }

        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), status.value());
        return new ResponseEntity<>(errorResponse, status);
    }
}

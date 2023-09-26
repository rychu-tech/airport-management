package com.airport.manager.project;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponse {
    private String message;
    private int code;

    @JsonCreator
    public ErrorResponse(@JsonProperty("message") String message, @JsonProperty("code") int code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}

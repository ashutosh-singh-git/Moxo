package com.moxo.app.util;

import org.slf4j.Marker;
import org.springframework.http.HttpStatus;

public enum ResponseCode {

    M001(LoggingMarker.INVALID_REQUEST_ERROR, "Invalid value", HttpStatus.BAD_REQUEST);


    private Marker marker;
    private String message;
    private HttpStatus status;
    ResponseCode(Marker marker, String message, HttpStatus status) {
        this.marker = marker;
        this.message = message;
        this.status = status;
    }

    public Marker getMarker() {
        return marker;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}

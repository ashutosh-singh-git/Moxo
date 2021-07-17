package com.moxo.app.util;

import org.slf4j.Marker;
import org.springframework.http.HttpStatus;

public enum ResponseCode {

    M001(LoggingMarker.INVALID_REQUEST_ERROR, "Invalid value", HttpStatus.BAD_REQUEST),
    M002(LoggingMarker.ACTIVITY_POST_ERROR, "Invalid value", HttpStatus.INTERNAL_SERVER_ERROR),
    M003(LoggingMarker.ACTIVITY_NOT_FOUND, "Activity not found", HttpStatus.NOT_FOUND);


    private final Marker marker;
    private final String message;
    private final HttpStatus status;

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

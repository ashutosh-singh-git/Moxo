package com.moxo.app.util;

import org.slf4j.Marker;
import org.springframework.http.HttpStatus;

public enum ResponseCode {

    M001(LoggingMarker.INVALID_REQUEST_ERROR, "Invalid value", HttpStatus.BAD_REQUEST),
    M002(LoggingMarker.ACTIVITY_POST_ERROR, "Invalid value", HttpStatus.INTERNAL_SERVER_ERROR),
    M003(LoggingMarker.ACTIVITY_NOT_FOUND, "Activity not found", HttpStatus.NOT_FOUND),

    P001(LoggingMarker.PAW_PAGE_NOT_FOUND, "Paw Page not found", HttpStatus.NOT_FOUND),
    P002(LoggingMarker.FOSTER_NOT_FOUND, "Foster Details not found", HttpStatus.NOT_FOUND),
    P003(LoggingMarker.FOSTER_UPDATE_ERROR, "Foster update error", HttpStatus.NOT_FOUND),
    P004(LoggingMarker.USER_REGISTER_ERROR, "User register error", HttpStatus.INTERNAL_SERVER_ERROR),
    P005(LoggingMarker.USER_REGISTER_ERROR, "User not found error", HttpStatus.BAD_REQUEST),
    P006(LoggingMarker.CLIENT_CONFIG_ERROR, "Client Config error", HttpStatus.INTERNAL_SERVER_ERROR),
    P007(LoggingMarker.POST_NOT_FOUND, "User Login error", HttpStatus.NOT_FOUND),
    P008(LoggingMarker.POST_CREATION_ERROR, "User Login error", HttpStatus.INTERNAL_SERVER_ERROR);


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

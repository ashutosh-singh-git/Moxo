package com.moxo.app.util;

import lombok.Getter;
import org.slf4j.Marker;
import org.springframework.http.HttpStatus;

@Getter
public class MoxoException extends RuntimeException {

    private final String message;
    private final String errorCode;
    private Marker marker;
    private final HttpStatus status;

    public MoxoException(ResponseCode code, String message, Throwable cause) {
        super(message, cause);
        this.message = message;
        this.errorCode = code.name();
        this.marker = code.getMarker();
        this.status = code.getStatus();
    }

    public MoxoException(ResponseCode code, String message) {
        super(message);
        this.message = message;
        this.errorCode = code.name();
        this.status = code.getStatus();
    }

    public MoxoException(ResponseCode code) {
        super(code.getMessage());
        this.message = code.getMessage();
        this.errorCode = code.name();
        this.status = code.getStatus();
    }

}

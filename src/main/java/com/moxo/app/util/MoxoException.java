package com.moxo.app.util;

import lombok.Getter;
import org.slf4j.Marker;

@Getter
public class MoxoException extends RuntimeException {

    private String message;
    private String errorCode;
    private Marker marker;

    public MoxoException(ResponseCode code, String message, Throwable cause) {
        super(message, cause);
        this.message = message;
        this.errorCode = code.name();
        this.marker = code.getMarker();
    }

    public MoxoException(ResponseCode code, String message) {
        super(message);
        this.message = message;
        this.errorCode = code.name();
    }

    public MoxoException(ResponseCode code) {
        super(code.getMessage());
        this.message = code.getMessage();
        this.errorCode = code.name();
    }

}

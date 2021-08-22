package com.moxo.app.util;

import com.moxo.app.dto.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({MoxoException.class})
    public ResponseEntity<Object> handleControllerException(MoxoException ex) {
        LOGGER.error(ex.getMarker(), ex.getMessage(), ex);
        BaseResponse build = BaseResponse.builder()
                .msg(ex.getMessage())
                .success(false)
                .errorCode(ex.getErrorCode())
                .build();
        return ResponseEntity
                .status(ex.getStatus() != null ? ex.getStatus() : HttpStatus.INTERNAL_SERVER_ERROR)
                .body(build);
    }
}

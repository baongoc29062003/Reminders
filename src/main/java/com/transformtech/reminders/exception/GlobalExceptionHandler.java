package com.transformtech.reminders.exception;

import com.transformtech.reminders.enums.ErrorCode;
import com.transformtech.reminders.resp.ErrorResp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResp> handleNotFoundException(ResourceNotFoundException ex) {
        ErrorCode code = ex.getErrorCode();

        return ResponseEntity
                .status(code.getHttpStatus())
                .body(new ErrorResp(code.getCode(),code.getMessage()));
    }
}

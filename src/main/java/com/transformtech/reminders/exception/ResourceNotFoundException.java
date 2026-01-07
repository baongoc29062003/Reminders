package com.transformtech.reminders.exception;

import com.transformtech.reminders.enums.ErrorCode;


public class ResourceNotFoundException extends RuntimeException {
    private final ErrorCode errorCode;
    public ResourceNotFoundException(ErrorCode error) {
        super(error.getMessage());
        this.errorCode = error;
    }
    public ErrorCode getErrorCode() {
        return errorCode;
    }
}

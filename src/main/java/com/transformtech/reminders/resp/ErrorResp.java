package com.transformtech.reminders.resp;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorResp {

    private final String code;
    private final String message;
    private final LocalDateTime timestamp;

    public ErrorResp (String code, String message) {
        this.code = code;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

}

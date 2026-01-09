package com.transformtech.reminders.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@AllArgsConstructor
@Getter
public enum ErrorCode {
    TASK_NOT_FOUND("E001","Không tìm thấy Task",HttpStatus.NOT_FOUND),
    TASK_DETAIL_NOT_FOUND("E002","Không tìm thấy Task Detail",HttpStatus.NOT_FOUND);

    private String code;
    private String message;
    private HttpStatus httpStatus;
}

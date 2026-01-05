package com.transformtech.reminders.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AbstractDTO {
    private Long id;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}

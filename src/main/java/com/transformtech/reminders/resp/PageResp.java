package com.transformtech.reminders.resp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class PageResp<T> {
    private List<T> content;
    private long totalElements;
    private long totalPages;
    private int pageNumber;

    public PageResp (Page<T> page) {
        this.content = page.getContent();
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.pageNumber = page.getNumber();
    }

}

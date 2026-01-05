package com.transformtech.reminders.entity;


import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;



@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="crateddate")
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name ="modifieddate")
    @LastModifiedDate
    private LocalDateTime modifiedDate;

    public Long getId() {
        return id;
    }


    public LocalDateTime getCreatedDate() {
        return createdDate;
    }


    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

}

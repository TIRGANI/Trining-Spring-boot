package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
@MappedSuperclass
@Getter
@Setter
public abstract class  BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_at",updatable = false)
    private Date createAt;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date deleteAt;

    private boolean isActive;
    private boolean deleted;

    @PrePersist
    protected void onCreate() {
        createAt = new Date();
        updateAt = new Date();
        deleteAt = new Date();
    }



}

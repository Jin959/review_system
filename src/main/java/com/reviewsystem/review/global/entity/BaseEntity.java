package com.reviewsystem.review.global.entity;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public class BaseEntity {
    @CreationTimestamp
    private LocalDateTime createTime;

    @UpdateTimestamp
    private LocalDateTime updateTime;

    @NotNull
    @Enumerated(EnumType.STRING)
    protected DeletionStatus deletionStatus = DeletionStatus.UNDELETED;

    public enum DeletionStatus {
        DELETED, UNDELETED;
    }
}

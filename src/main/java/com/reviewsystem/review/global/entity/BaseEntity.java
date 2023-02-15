package com.reviewsystem.review.global.entity;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public class BaseEntity {
    @CreationTimestamp
    @NotNull
    private LocalDateTime createTime;

    @UpdateTimestamp
    @NotNull
    private LocalDateTime updateTime;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Size(max = 10)
    protected DeletionStatus deletionStatus = DeletionStatus.UNDELETED;

    public enum DeletionStatus {
        DELETED, UNDELETED;
    }
}

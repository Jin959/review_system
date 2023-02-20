package com.reviewsystem.review.user.repository;

import com.reviewsystem.review.global.entity.BaseEntity;
import com.reviewsystem.review.user.entity.Irumi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IrumiRepository extends JpaRepository<Irumi, Long> {
    Optional<Irumi> findByIdAndDeletionStatus(Long irumiId, BaseEntity.DeletionStatus undeleted);
}

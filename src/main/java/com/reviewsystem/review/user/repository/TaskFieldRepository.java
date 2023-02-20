package com.reviewsystem.review.user.repository;

import com.reviewsystem.review.global.entity.BaseEntity;
import com.reviewsystem.review.user.entity.ability.TaskField;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskFieldRepository extends JpaRepository<TaskField, Long> {

    Optional<TaskField> findByIrumiIdAndCategorySmallAndDeletionStatus(Long irumiId, String categorySmall, BaseEntity.DeletionStatus undeleted);

    Optional<TaskField> findByIdAndDeletionStatus(Long abilityId, BaseEntity.DeletionStatus undeleted);

    List<TaskField> findByIrumiIdAndDeletionStatus(Long irumiId, BaseEntity.DeletionStatus undeleted);
}

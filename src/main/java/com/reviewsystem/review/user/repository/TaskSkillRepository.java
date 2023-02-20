package com.reviewsystem.review.user.repository;

import com.reviewsystem.review.global.entity.BaseEntity;
import com.reviewsystem.review.user.entity.ability.TaskSkill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskSkillRepository extends JpaRepository<TaskSkill, Long> {
    Optional<TaskSkill> findByIrumiIdAndCategorySmallAndDeletionStatus(Long irumiId, String categorySmall, BaseEntity.DeletionStatus undeleted);

    Optional<TaskSkill> findByIdAndDeletionStatus(Long abilityId, BaseEntity.DeletionStatus undeleted);

    List<TaskSkill> findByIrumiIdAndDeletionStatus(Long irumiId, BaseEntity.DeletionStatus undeleted);
}

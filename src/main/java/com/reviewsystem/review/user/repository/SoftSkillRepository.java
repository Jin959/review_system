package com.reviewsystem.review.user.repository;

import com.reviewsystem.review.global.entity.BaseEntity;
import com.reviewsystem.review.user.entity.ability.SoftSkill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SoftSkillRepository extends JpaRepository<SoftSkill, Long> {

    Optional<SoftSkill> findByIrumiIdAndCategorySmallAndDeletionStatus(Long irumiId, String categorySmall, BaseEntity.DeletionStatus undeleted);

    Optional<SoftSkill> findByIdAndDeletionStatus(Long abilityId, BaseEntity.DeletionStatus undeleted);

    List<SoftSkill> findByIrumiIdAndDeletionStatus(Long irumiId, BaseEntity.DeletionStatus undeleted);
}

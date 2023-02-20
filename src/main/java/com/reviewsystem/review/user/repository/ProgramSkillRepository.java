package com.reviewsystem.review.user.repository;

import com.reviewsystem.review.global.entity.BaseEntity;
import com.reviewsystem.review.user.entity.ability.ProgramSkill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProgramSkillRepository extends JpaRepository<ProgramSkill, Long> {

    Optional<ProgramSkill> findByIrumiIdAndCategorySmallAndDeletionStatus(Long irumiId, String categorySmall, BaseEntity.DeletionStatus undeleted);

    Optional<ProgramSkill> findByIdAndDeletionStatus(Long abilityId, BaseEntity.DeletionStatus undeleted);

    List<ProgramSkill> findByIrumiIdAndDeletionStatus(Long irumiId, BaseEntity.DeletionStatus undeleted);
}

package com.reviewsystem.review.user.repository;

import com.reviewsystem.review.global.entity.BaseEntity;
import com.reviewsystem.review.user.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>  {
    Optional<User> findByIdAndDeletionStatus(Long Id, BaseEntity.DeletionStatus undeleted);

    Optional<User> findByEmailAndDeletionStatus(String email, BaseEntity.DeletionStatus undeleted);
}

package com.reviewsystem.review.user.repository;

import com.reviewsystem.review.user.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
}

package com.reviewsystem.review.user.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Column(name = "address_state")
    @Size(max = 10)
    private String State;

    @Column(name = "address_city")
    @Size(max = 10)
    private String City;
}

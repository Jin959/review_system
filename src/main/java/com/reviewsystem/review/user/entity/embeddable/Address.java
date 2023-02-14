package com.reviewsystem.review.user.entity.embeddable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Embeddable
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Column(name = "address_state")
    @Size(max = 10)
    private String state;

    @Column(name = "address_city")
    @Size(max = 10)
    private String city;
}

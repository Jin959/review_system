package com.reviewsystem.review.user.entity.user;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Column(name = "address_state")
    @Size(max = 10)
    @NotNull
    String state;

    @Column(name = "address_city")
    @Size(max = 10)
    @NotNull
    String city;
}

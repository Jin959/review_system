package com.reviewsystem.review.user.entity.user;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;


@Embeddable
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Agreement {
    @NotNull
    @ColumnDefault("1")
    Boolean serviceAgreement;

    @NotNull
    @ColumnDefault("1")
    Boolean personalDataAgreement;

    @NotNull
    @ColumnDefault("0")
    Boolean marketingAgreement;
}

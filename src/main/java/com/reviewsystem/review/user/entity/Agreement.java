package com.reviewsystem.review.user.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Agreement {
    @NotNull
    @ColumnDefault("1")
    private Boolean serviceAgreement;

    @NotNull
    @ColumnDefault("1")
    private Boolean personalDataAgreement;

    @NotNull
    @ColumnDefault("0")
    private Boolean marketingAgreement;
}

package com.reviewsystem.review.user.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
@ToString
@EqualsAndHashCode
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

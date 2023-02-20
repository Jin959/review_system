package com.reviewsystem.review.user.entity.user;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Optional;


@Embeddable
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Agreement {
    @Column(nullable = false)
    @ColumnDefault("1")
    Boolean serviceAgreement;

    @Column(nullable = false)
    @ColumnDefault("1")
    Boolean personalDataAgreement;

    @Column(nullable = false)
    @ColumnDefault("0")
    Boolean marketingAgreement;

    @PrePersist
    public void prePersist() {
        marketingAgreement = Optional.ofNullable(marketingAgreement)
                .orElseGet(() -> false);
    }

    @PreUpdate
    public void preUpdate() {
        marketingAgreement = Optional.ofNullable(marketingAgreement)
                .orElseGet(() -> false);
    }
}

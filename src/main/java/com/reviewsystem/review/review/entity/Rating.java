package com.reviewsystem.review.review.entity;

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
public class Rating {
    @NotNull
    @ColumnDefault("0")
    private Integer taskSkillRating;

    @NotNull
    @ColumnDefault("0")
    private Integer programSkillRating;

    @NotNull
    @ColumnDefault("0")
    private Integer softSkillRating;
}

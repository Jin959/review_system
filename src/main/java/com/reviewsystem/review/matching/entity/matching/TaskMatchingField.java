package com.reviewsystem.review.matching.entity.matching;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class TaskMatchingField {
    @NotNull
    @Column(name = "task_field_category_big")
    private String categoryBig;

    @NotNull
    @Column(name = "task_field_category_small")
    private String categorySmall;

}

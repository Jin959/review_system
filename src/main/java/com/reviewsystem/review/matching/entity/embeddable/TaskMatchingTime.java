package com.reviewsystem.review.matching.entity.embeddable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Embeddable
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class TaskMatchingTime {
    @NotNull
    private LocalDateTime startingTime;
    @NotNull
    private LocalTime takingTime;
}

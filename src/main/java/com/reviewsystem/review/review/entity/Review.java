package com.reviewsystem.review.review.entity;

import com.reviewsystem.review.global.entity.BaseEntity;
import com.reviewsystem.review.matching.entity.matching.TaskMatching;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Rating rating;

    @Size(max = 5000)
    private String details;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "matching_id")
    @ToString.Exclude
    private TaskMatching taskMatching;

    @Enumerated(EnumType.STRING)
    private Recommendation recommendation = Recommendation.RECOMMEND;

    public enum Recommendation {
        RECOMMEND, NONRECOMMEND
    }

    @Builder
    public Review(Integer taskSkillRating, Integer programSkillRating,
                  Integer softSkillRating, String details,
                  TaskMatching taskMatching, Recommendation recommendation) {
        this.rating = new Rating(taskSkillRating, programSkillRating, softSkillRating);
        this.details = details;
        this.taskMatching = taskMatching;
        this.recommendation = recommendation;
    }
}

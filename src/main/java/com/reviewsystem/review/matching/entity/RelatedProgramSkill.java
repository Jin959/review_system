package com.reviewsystem.review.matching.entity;

import com.reviewsystem.review.global.Entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RelatedProgramSkill extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String programSkillCategorySmall;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matching_id")
    @ToString.Exclude
    private TaskMatching taskMatching;

    @Builder
    public RelatedProgramSkill(String programSkillCategorySmall, TaskMatching taskMatching) {
        this.programSkillCategorySmall = programSkillCategorySmall;
        this.taskMatching = taskMatching;
    }
}

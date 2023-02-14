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
public class RelatedTaskSkill extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String taskSkillCategoryBig;

    @NotNull
    private String taskSkillCategorySmall;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matching_id")
    @ToString.Exclude
    private TaskMatching taskMatching;

    @Builder
    public RelatedTaskSkill(String taskSkillCategoryBig,
                            String taskSkillCategorySmall, TaskMatching taskMatching) {
        this.taskSkillCategoryBig = taskSkillCategoryBig;
        this.taskSkillCategorySmall = taskSkillCategorySmall;
        this.taskMatching = taskMatching;
    }
}

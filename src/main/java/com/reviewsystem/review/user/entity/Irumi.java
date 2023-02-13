package com.reviewsystem.review.user.entity;

import com.reviewsystem.review.global.Entity.RootEntity;
import com.reviewsystem.review.user.entity.Ability.ProgramSkill;
import com.reviewsystem.review.user.entity.Ability.SoftSkill;
import com.reviewsystem.review.user.entity.Ability.TaskField;
import com.reviewsystem.review.user.entity.Ability.TaskSkill;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Irumi extends RootEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 10)
    private String finalDegree;

    @NotNull
    @Size(max = 10)
    private String major;

    @NotNull
    @Size(max = 10)
    private String schoolName;

    @NotNull
    @Size(max = 10)
    private String employment;

    @NotNull
    @Size(max = 10)
    private String job;

    @NotNull
    @Size(max = 10)
    private String workExperience;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private TaskField taskField;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private TaskSkill taskSkill;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private SoftSkill softSkill;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ProgramSkill programSkill;

    @Enumerated(EnumType.STRING)
    private IrumiEducationState irumiEducationState;

    private enum IrumiEducationState {
        UNTAKEN, TAKEN
    }
}

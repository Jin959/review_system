package com.reviewsystem.review.user.entity;

import com.reviewsystem.review.global.Entity.RootEntity;
import com.reviewsystem.review.user.entity.Ability.ProgramSkill;
import com.reviewsystem.review.user.entity.Ability.SoftSkill;
import com.reviewsystem.review.user.entity.Ability.TaskField;
import com.reviewsystem.review.user.entity.Ability.TaskSkill;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
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
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;

    @OneToMany(mappedBy = "irumi", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<TaskField> taskFieldList;

    @OneToMany(mappedBy = "irumi", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<TaskSkill> taskSkillList;

    @OneToMany(mappedBy = "irumi", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<SoftSkill> softSkillList;

    @OneToMany(mappedBy = "irumi", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<ProgramSkill> programSkillList;

    @Enumerated(EnumType.STRING)
    private IrumiEducationState irumiEducationState = IrumiEducationState.UNTAKEN;

    private enum IrumiEducationState {
        UNTAKEN, TAKEN
    }

    @Builder
    public Irumi(String finalDegree, String major, String schoolName,
                 String employment, String job, String workExperience, User user) {
        this.finalDegree = finalDegree;
        this.major = major;
        this.schoolName = schoolName;
        this.employment = employment;
        this.job = job;
        this.workExperience = workExperience;
        this.user = user;
    }
}

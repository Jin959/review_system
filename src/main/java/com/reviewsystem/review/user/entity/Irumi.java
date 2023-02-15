package com.reviewsystem.review.user.entity;

import com.reviewsystem.review.global.entity.BaseEntity;
import com.reviewsystem.review.matching.entity.IrumiMatchingMapping;
import com.reviewsystem.review.user.entity.ability.ProgramSkill;
import com.reviewsystem.review.user.entity.ability.SoftSkill;
import com.reviewsystem.review.user.entity.ability.TaskField;
import com.reviewsystem.review.user.entity.ability.TaskSkill;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Irumi extends BaseEntity {
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
    private List<TaskField> taskFieldList = new ArrayList<>();

    @OneToMany(mappedBy = "irumi", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<TaskSkill> taskSkillList = new ArrayList<>();

    @OneToMany(mappedBy = "irumi", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<SoftSkill> softSkillList = new ArrayList<>();

    @OneToMany(mappedBy = "irumi", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<ProgramSkill> programSkillList = new ArrayList<>();

    @OneToMany(mappedBy = "irumi", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<IrumiMatchingMapping> irumiMatchingMappingList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private IrumiEducationState irumiEducationState = IrumiEducationState.UNTAKEN;

    public enum IrumiEducationState {
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

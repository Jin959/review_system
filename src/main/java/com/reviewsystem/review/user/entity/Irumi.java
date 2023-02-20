package com.reviewsystem.review.user.entity;

import com.reviewsystem.review.global.entity.BaseEntity;
import com.reviewsystem.review.matching.entity.IrumiMatchingMapping;
import com.reviewsystem.review.user.entity.ability.ProgramSkill;
import com.reviewsystem.review.user.entity.ability.SoftSkill;
import com.reviewsystem.review.user.entity.ability.TaskField;
import com.reviewsystem.review.user.entity.ability.TaskSkill;
import com.reviewsystem.review.user.entity.user.User;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
public class Irumi extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @NotNull
    @Size(max = 10)
    private String finalDegree;

    @Size(max = 10)
    @Column(nullable = false)
    @ColumnDefault("'NONE'")
    private String major;

    @Size(max = 10)
    @Column(nullable = false)
    @ColumnDefault("'NONE'")
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
    @NotNull
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
    @NotNull
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

    public void updateIrumi(String finalDegree, String major, String schoolName,
                            String employment, String job, String workExperience) {
        Optional.ofNullable(finalDegree).ifPresent(d -> this.finalDegree = d);
        this.major = Optional.ofNullable(major).orElse("NONE");
        this.schoolName = Optional.ofNullable(schoolName).orElse("NONE");

        Optional.ofNullable(employment).ifPresent(e -> this.employment = e);
        Optional.ofNullable(job).ifPresent(j -> this.job = j);
        Optional.ofNullable(workExperience).ifPresent(w -> this.workExperience = w);

//        Arrays.stream(new String[]{finalDegree, employment, job, workExperience})
//                .forEach((para) -> Optional.ofNullable(para).ifPresent(
//                        (para_) -> {
//                            Field field = this.getClass().getField();
//                        }
//                ));
    }
}

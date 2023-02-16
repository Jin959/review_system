package com.reviewsystem.review.matching.entity.matching;

import com.reviewsystem.review.global.entity.BaseEntity;
import com.reviewsystem.review.matching.entity.IrumiMatchingMapping;
import com.reviewsystem.review.matching.entity.RelatedTaskSkill;
import com.reviewsystem.review.review.entity.Review;
import com.reviewsystem.review.user.entity.Customer;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TaskMatching extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 100)
    private String taskName;

    @Embedded
    private TaskMatchingField taskMatchingField;

    @Embedded
    private TaskMatchingTime taskMatchingTime;

    @NotNull
    private String details;

    @ColumnDefault("'NONE'")
    private String referenceFileUrl;

    @Enumerated(EnumType.STRING)
    private MatchingType matchingType = MatchingType.AUTO;

    @Enumerated(EnumType.STRING)
    private ResultSending resultSending = ResultSending.MYPAGE;

    @Enumerated(EnumType.STRING)
    private ZoomUse zoomUse = ZoomUse.NO_ZOOM;

    @Enumerated(EnumType.STRING)
    private MatchingStatus matchingStatus = MatchingStatus.NOW_MATCHING;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @ToString.Exclude
    private Customer customer;

    @OneToMany(mappedBy = "taskMatching", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<IrumiMatchingMapping> irumiMatchingMappingList = new ArrayList<>();

    @OneToMany(mappedBy = "taskMatching", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<RelatedTaskSkill> relatedTaskSkillList = new ArrayList<>();

    @OneToOne(mappedBy = "taskMatching")
    @ToString.Exclude
    private Review review;

    public enum MatchingType {
        AUTO, REMATCH, ADVICE,
    }

    public enum ResultSending {
        MYPAGE, EMAIL
    }

    public enum ZoomUse {
        USE_ZOOM, NO_ZOOM
    }

    public enum MatchingStatus {
        NOW_MATCHING, NOT_STARTED, DOING, DONE
    }

    @Builder
    public TaskMatching(String taskName, String fieldCategoryBig, String fieldCategorySmall,
                        LocalDateTime startingTime, LocalTime takingTime, String details,
                        String referenceFileUrl, MatchingType matchingType, ResultSending resultSending,
                        ZoomUse zoomUse, MatchingStatus matchingStatus, Customer customer) {
        this.taskName = taskName;
        this.taskMatchingField = new TaskMatchingField(fieldCategoryBig, fieldCategorySmall);
        this.taskMatchingTime = new TaskMatchingTime(startingTime, takingTime);
        this.details = details;
        this.referenceFileUrl = referenceFileUrl;
        this.matchingType = matchingType;
        this.resultSending = resultSending;
        this.zoomUse = zoomUse;
        this.matchingStatus = matchingStatus;
        this.customer = customer;
    }
}

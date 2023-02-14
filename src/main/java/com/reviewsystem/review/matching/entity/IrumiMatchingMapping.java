package com.reviewsystem.review.matching.entity;

import com.reviewsystem.review.global.Entity.BaseEntity;
import com.reviewsystem.review.user.entity.Irumi;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class IrumiMatchingMapping extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "irumi_id")
    @ToString.Exclude
    private Irumi irumi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matching_id")
    @ToString.Exclude
    private TaskMatching taskMatching;

    @Builder
    public IrumiMatchingMapping(Irumi irumi, TaskMatching taskMatching) {
        this.irumi = irumi;
        this.taskMatching = taskMatching;
    }
}

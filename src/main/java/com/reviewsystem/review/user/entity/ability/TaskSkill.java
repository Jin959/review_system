package com.reviewsystem.review.user.entity.ability;

import com.reviewsystem.review.user.entity.Irumi;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
public class TaskSkill extends Ability {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "irumi_id")
    @ToString.Exclude
    private Irumi irumi;

    @Builder
    public TaskSkill(String categoryBig, String categorySmall, Integer rating, Irumi irumi) {
        super(categoryBig, categorySmall, rating);
        this.irumi = irumi;
    }
}

package com.reviewsystem.review.user.entity.ability;

import com.reviewsystem.review.user.entity.Irumi;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProgramSkill extends Ability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "irumi_id")
    @ToString.Exclude
    private Irumi irumi;

    @Builder
    public ProgramSkill(String categoryBig, String categorySmall, Integer rating, Irumi irumi) {
        super(categoryBig, categorySmall, rating);
        this.irumi = irumi;
    }
}

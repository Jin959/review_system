package com.reviewsystem.review.user.entity.ability;

import com.reviewsystem.review.global.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class Ability extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @NotNull
    @Size(max = 20)
    private String categoryBig;

    @NotNull
    @Size(max = 20)
    private String categorySmall;

    @NotNull
    private Integer rating;

    public Ability(String categoryBig, String categorySmall, Integer rating) {
        this.categoryBig = categoryBig;
        this.categorySmall = categorySmall;
        this.rating = rating;
    }
}

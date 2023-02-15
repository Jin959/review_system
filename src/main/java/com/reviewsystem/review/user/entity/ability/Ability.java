package com.reviewsystem.review.user.entity.ability;

import com.reviewsystem.review.global.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class Ability extends BaseEntity {

    @NotNull
    @Size(max = 20)
    private String categoryBig;

    @NotNull
    @Size(max = 20)
    private String categorySmall;

    @NotNull
    @ColumnDefault("0")
    private Integer rating;
}

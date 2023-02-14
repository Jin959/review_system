package com.reviewsystem.review.user.entity.Ability;

import com.reviewsystem.review.global.Entity.BaseEntity;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Getter
@MappedSuperclass
public class Ability extends BaseEntity {

    @Size(max = 20)
    private String categoryBig;

    @Size(max = 20)
    private String categorySmall;

    @ColumnDefault("0")
    @Max(5)
    @Min(0)
    private Integer rating;
}

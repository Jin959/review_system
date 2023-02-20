package com.reviewsystem.review.user.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AbilityReq {
    @NotNull(message = "능력의 대분류를 정해주세요.")
    private String categoryBig;
    @NotNull(message = "능력의 소분류를 정해주세요.")
    private String categorySmall;

    @NotNull(message = "본인이 선택한 업무 중 책정되지 않은 역량이 존재합니다.")
    @Max(value = 5, message = "최대 5점 까지 가능합니다.")
    @Min(value = 1, message = "최소 1점 부터 가능합니다.")
    private Integer rating;
}

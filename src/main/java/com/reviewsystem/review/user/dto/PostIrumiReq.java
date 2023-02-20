package com.reviewsystem.review.user.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PostIrumiReq {

    @NotNull(message = "최종 학력을 선택해주세요.")
    private String finalDegree;

    private String major;

    private String schoolName;

    @NotNull(message = "현재 재직상황을 선택해주세요.")
    private String employment;

    @NotNull(message = "현재(또는 과거) 직업을 선택해주세요.")
    private String job;

    @NotNull(message = "업무 경력을 선택해주세요.")
    private String workExperience;
}

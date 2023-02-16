package com.reviewsystem.review.user.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PatchUserInfoReq {
    @Pattern(
            regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$",
            message = "이메일 형식에 부합하지 않습니다."
    )
    private String email;

    @NotNull(message = "마케팅 이용약관에 대한 선택을 해주세요.")
    private Boolean marketingAgreement; // 선택동의

    @Size(max = 10, message = "행정 시,군이 너무 깁니다. 최대 10자 입니다.")
    @NotNull(message = "주소 정보를 입력해주세요.")
    private String state;

    @Size(max = 10, message = "행정 동,구가 너무 깁니다. 최대 10자 입니다.")
    @NotNull(message = "주소 정보를 입력해주세요.")
    private String city;
}

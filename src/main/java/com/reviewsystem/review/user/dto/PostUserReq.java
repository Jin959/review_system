package com.reviewsystem.review.user.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PostUserReq {

    @Pattern(
            regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$",
            message = "이메일 형식에 부합하지 않습니다."
    )
    private String email;

    @Size(min = 8, max = 16, message = "비밀번호는 8자 이상, 16자 이하여야 합니다.")
    @NotNull(message = "비밀번호는 8자 이상, 16자 이하여야 합니다.")
    private String password;

    @Pattern(
            regexp = "null|^01([0|1|6|7|8|9])-([0-9]{3,4})-([0-9]{4})$",
            message = "휴대폰 번호 형식에 부합하지 않습니다. '-'로 구분되는 010-1234-1234 와 같이 '(3자리)-(3 또는 4자리)-(4자리)' 형식으로 제출해주세요."
    )
    @Size(max = 20, message = "휴대폰 번호는 20자 이하여야 합니다.")
    private String phone;

    @NotNull(message = "서비스 이용약관에 동의해주세요.")
    @AssertTrue(message = "서비스 이용약관에 동의해주세요.")
    private Boolean serviceAgreement;

    @NotNull(message = "개인정보 이용약관에 동의해주세요.")
    @AssertTrue(message = "개인정보 이용약관에 동의해주세요.")
    private Boolean personalDataAgreement;

//    @NotNull(message = "마케팅 이용약관에 대한 선택을 해주세요.")
    private Boolean marketingAgreement; // 선택동의

    @Size(max = 10, message = "행정 시,군이 너무 깁니다. 최대 10자 입니다.")
    @NotNull(message = "주소 정보를 입력해주세요.")
    private String state;

    @Size(max = 10, message = "행정 동,구가 너무 깁니다. 최대 10자 입니다.")
    @NotNull(message = "주소 정보를 입력해주세요.")
    private String city;
}

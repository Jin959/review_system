package com.reviewsystem.review.user.dto;

import com.reviewsystem.review.user.entity.user.User;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GetUserRes {
    private Long userId;

    private String email;

    private String phone;

    private Agreement agreement;

    private Address address;

    private String oauthType;

    public GetUserRes(User user) {
        this.userId = user.getId();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.agreement = new Agreement();
        this.agreement.serviceAgreement = user.getAgreement().getServiceAgreement();
        this.agreement.personalDataAgreement = user.getAgreement().getPersonalDataAgreement();
        this.agreement.marketingAgreement = user.getAgreement().getMarketingAgreement();
        this.address = new Address();
        this.address.state = user.getAddress().getState();
        this.address.city = user.getAddress().getCity();
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public class Address {
        private String state;

        private String city;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public class Agreement {
        private Boolean serviceAgreement;

        private Boolean personalDataAgreement;

        private Boolean marketingAgreement; // 선택동의
    }
}

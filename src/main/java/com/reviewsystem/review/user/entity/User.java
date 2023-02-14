package com.reviewsystem.review.user.entity;

import com.reviewsystem.review.global.Entity.RootEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends RootEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    private String email;

    @Size(max = 200)
    @ColumnDefault("'OAUTH'")
    private String password;


    @Size(max = 20)
    @ColumnDefault("'NONE'")
    private String phone;


    @Embedded
    private Agreement agreement;

    @Embedded
    private Address address;

    @NotNull
    @Enumerated(EnumType.STRING)
    private OauthType oauthType = OauthType.NONE;

    public enum OauthType {
        NONE, KAKAO
    }

    @Builder
    public User(String email, String password, String phone,
                String state, String city, Boolean serviceAgreement,
                Boolean personalDataAgreement, Boolean marketingAgreement,
                OauthType oauthType) {
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.agreement = new Agreement(serviceAgreement, personalDataAgreement, marketingAgreement);
        this.address = new Address(state, city);
        this.oauthType = oauthType;
    }
}

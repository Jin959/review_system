package com.reviewsystem.review.user.entity.user;

import com.reviewsystem.review.global.entity.BaseEntity;
import com.reviewsystem.review.user.dto.PatchUserInfoReq;
import com.reviewsystem.review.user.dto.PatchUserPasswordReq;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Optional;

@Entity
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @NotNull
    @Size(max = 50)
    private String email;

    @Size(max = 200)
    @ColumnDefault("'OAUTH'")
    @Column(nullable = false)
    private String password;

    @Size(max = 20)
    @ColumnDefault("'NONE'")
    @Column(nullable = false)
    private String phone;

    @Embedded
    private Agreement agreement;

    @Embedded
    @Valid
    @NotNull
    private Address address;

    @Enumerated(EnumType.STRING)
    @NotNull
    private OauthType oauthType = OauthType.NONE;

    @Builder
    public User(String email, String password, String phone,
                String state, String city, Boolean serviceAgreement,
                Boolean personalDataAgreement, Boolean marketingAgreement) {
        this.email = email;
        this.password = password;
        this.phone = phone;

        this.agreement = new Agreement();
        this.agreement.serviceAgreement = serviceAgreement;
        this.agreement.personalDataAgreement = personalDataAgreement;
        this.agreement.marketingAgreement = marketingAgreement;

        this.address = new Address(state, city);
    }

    public void updateUserInfo(PatchUserInfoReq patchUserInfoReq) {
        this.email = patchUserInfoReq.getEmail();
        this.agreement.marketingAgreement = patchUserInfoReq.getMarketingAgreement();
        this.address.state = patchUserInfoReq.getState();
        this.address.city = patchUserInfoReq.getCity();
    }

    public void updateUserPassword(PatchUserPasswordReq patchUserPasswordReq) {
        this.password = patchUserPasswordReq.getPassword();
    }

    public void updateDeleteStatus(User user) {
        this.deletionStatus = DeletionStatus.DELETED;
    }

    public enum OauthType {
        NONE, KAKAO
    }
}

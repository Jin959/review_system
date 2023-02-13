package com.reviewsystem.review.user.entity;

import com.reviewsystem.review.global.Entity.RootEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
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

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Irumi irumi;

    public enum OauthType {
        NONE, KAKAO
    }
}

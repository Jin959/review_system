package com.reviewsystem.review.user;

import com.reviewsystem.review.global.exception.BaseException;
import com.reviewsystem.review.user.dto.*;
import com.reviewsystem.review.user.entity.user.User;
import com.reviewsystem.review.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.reviewsystem.review.global.Response.ResponseHttpStatus.*;
import static com.reviewsystem.review.global.entity.BaseEntity.DeletionStatus.UNDELETED;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    public PostUserRes createUser(PostUserReq postUserReq) {
        Optional<User> checkEmail = userRepository.findByEmailAndDeletionStatus(postUserReq.getEmail(), UNDELETED);
        checkEmail.ifPresent(email -> {
            throw new BaseException(BAD_REQ_USER_EXISTS_EMAIL);
        });

        try {
            User user = User.builder()
                    .email(postUserReq.getEmail())
                    .password(postUserReq.getPassword())
                    .phone(postUserReq.getPhone())
                    .serviceAgreement(postUserReq.getServiceAgreement())
                    .personalDataAgreement(postUserReq.getPersonalDataAgreement())
                    .marketingAgreement(postUserReq.getMarketingAgreement())
                    .state(postUserReq.getState())
                    .city(postUserReq.getCity())
                    .build();
            userRepository.save(user);

            return new PostUserRes(user.getId(), user.getOauthType().name());
        } catch (Exception exception) {
            throw new BaseException(DB_CONNECTION_POOL_ERROR);
        }
    }

    @Transactional(readOnly = true)
    public GetUserRes getUserById(Long userId) {
        User user = userRepository.findByIdAndDeletionStatus(userId, UNDELETED)
                .orElseThrow(() -> new BaseException(NOT_FOUND_USER));
        return new GetUserRes(user);
    }


    public void updateUserInfo(Long userId, PatchUserInfoReq patchUserInfoReq) {
        Optional<User> checkEmail = userRepository.findByEmailAndDeletionStatus(patchUserInfoReq.getEmail(), UNDELETED);
        checkEmail.ifPresent(email -> {
            throw new BaseException(BAD_REQ_USER_EXISTS_EMAIL);
        });

        User user = userRepository.findByIdAndDeletionStatus(userId, UNDELETED)
                .orElseThrow(() -> new BaseException(NOT_FOUND_USER));

        try {
            user.updateUserInfo(patchUserInfoReq);
        } catch (Exception exception) {
            throw new BaseException(DB_CONNECTION_POOL_ERROR);
        }
    }

    public void updateUserPassword(Long userId, PatchUserPasswordReq patchUserPasswordReq) {
        User user = userRepository.findByIdAndDeletionStatus(userId, UNDELETED)
                .orElseThrow(() -> new BaseException(NOT_FOUND_USER));
        try {
            user.updateUserPassword(patchUserPasswordReq);
        } catch (Exception exception) {
            throw new BaseException(DB_CONNECTION_POOL_ERROR);
        }
    }

    public void deleteUser(Long userId) {
        userRepository.findById(userId).orElseThrow(() -> new BaseException(NOT_FOUND_USER));
        User user = userRepository.findByIdAndDeletionStatus(userId, UNDELETED)
                .orElseThrow(() -> new BaseException(NOT_FOUND_USER_ALREADY_DELETE));

        try {
            user.updateDeleteStatus(user);
        } catch (Exception exception) {
            throw new BaseException(DB_CONNECTION_POOL_ERROR);
        }
    }
}

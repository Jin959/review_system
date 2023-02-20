package com.reviewsystem.review.user;

import com.reviewsystem.review.global.exception.BaseException;
import com.reviewsystem.review.user.dto.*;
import com.reviewsystem.review.user.entity.Customer;
import com.reviewsystem.review.user.entity.Irumi;
import com.reviewsystem.review.user.entity.ability.*;
import com.reviewsystem.review.user.entity.user.User;
import com.reviewsystem.review.user.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.reviewsystem.review.global.Response.ResponseHttpStatus.*;
import static com.reviewsystem.review.global.entity.BaseEntity.DeletionStatus.UNDELETED;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final IrumiRepository irumiRepository;
    private final TaskFieldRepository taskFieldRepository;
    private final TaskSkillRepository taskSkillRepository;
    private final SoftSkillRepository softSkillRepository;
    private final ProgramSkillRepository programSkillRepository;

    public PostUserRes createUser(PostUserReq postUserReq) {
        Optional<User> checkEmail = userRepository.findByEmailAndDeletionStatus(postUserReq.getEmail(), UNDELETED);
        checkEmail.ifPresent(email -> {
            throw new BaseException(BAD_REQ_USER_EXISTS_EMAIL);
        });

        try {
        } catch (Exception exception) {
            throw new BaseException(DB_CONNECTION_POOL_ERROR);
        }
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

        Customer customer = Customer.builder()
                .user(user)
                .build();
        customerRepository.save(customer);

        return new PostUserRes(user.getId(), user.getOauthType().name());
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


    public PostIrumiRes createIrumiProfile(Long userId, PostIrumiReq postIrumiReq) {
        User user = userRepository.findByIdAndDeletionStatus(userId, UNDELETED)
                .orElseThrow(() -> new BaseException(NOT_FOUND_USER));
        try {
            Irumi irumi = Irumi.builder()
                    .finalDegree(postIrumiReq.getFinalDegree())
                    .major(postIrumiReq.getMajor())
                    .schoolName(postIrumiReq.getSchoolName())
                    .employment(postIrumiReq.getEmployment())
                    .job(postIrumiReq.getJob())
                    .workExperience(postIrumiReq.getWorkExperience())
                    .user(user)
                    .build();
            irumiRepository.save(irumi);

            return new PostIrumiRes(irumi.getId(), irumi.getIrumiEducationState().name());
        } catch (Exception exception) {
            throw new BaseException(DB_CONNECTION_POOL_ERROR);
        }
    }

    @Transactional(readOnly = true)
    public GetIrumiRes getIrumiProfile(Long irumiId) {
        Irumi irumi = irumiRepository.findByIdAndDeletionStatus(irumiId, UNDELETED)
                .orElseThrow(() -> new BaseException(NOT_FOUND_IRUMI));
        try {
            GetIrumiRes getIrumiRes = new GetIrumiRes(
                    irumi.getFinalDegree(), irumi.getMajor(), irumi.getSchoolName(),
                    irumi.getEmployment(), irumi.getJob(), irumi.getWorkExperience()
            );

            return getIrumiRes;
        } catch (Exception exception) {
            throw new BaseException(DB_CONNECTION_POOL_ERROR);
        }
    }

    public void updateIrumiProfile(Long irumiId, PatchIrumiReq patchIrumiReq) {
        Irumi irumi = irumiRepository.findByIdAndDeletionStatus(irumiId, UNDELETED)
                .orElseThrow(() -> new BaseException(NOT_FOUND_IRUMI));
        try {
            irumi.updateIrumi(
                    patchIrumiReq.getFinalDegree(),
                    patchIrumiReq.getMajor(),
                    patchIrumiReq.getSchoolName(),
                    patchIrumiReq.getEmployment(),
                    patchIrumiReq.getJob(),
                    patchIrumiReq.getWorkExperience()
            );
        } catch (Exception exception) {
            throw new BaseException(DB_CONNECTION_POOL_ERROR);
        }
    }

    public AbilityRes createIrumiAbility(Long irumiId, String ability, AbilityReq abilityReq) {
        Irumi irumi = irumiRepository.findByIdAndDeletionStatus(irumiId, UNDELETED)
                .orElseThrow(() -> new BaseException(NOT_FOUND_IRUMI));
        Ability abilityEntity;
        switch (ability) {
            case "task-field":
                taskFieldRepository.findByIrumiIdAndCategorySmallAndDeletionStatus(irumiId, abilityReq.getCategorySmall(), UNDELETED)
                        .ifPresent((a) -> {
                            throw new BaseException(BAD_REQ_ALREADY_EXIST_ABILITY);
                        });
                try {
                    TaskField taskField = TaskField.builder()
                            .categoryBig(abilityReq.getCategoryBig())
                            .categorySmall(abilityReq.getCategorySmall())
                            .rating(abilityReq.getRating())
                            .irumi(irumi)
                            .build();
                    abilityEntity = taskFieldRepository.save(taskField);
                } catch (Exception exception) {
                    throw new BaseException(DB_CONNECTION_POOL_ERROR);
                }
                break;
            case "task-skill":
                taskSkillRepository.findByIrumiIdAndCategorySmallAndDeletionStatus(irumiId, abilityReq.getCategorySmall(), UNDELETED)
                        .ifPresent((a) -> {
                            throw new BaseException(BAD_REQ_ALREADY_EXIST_ABILITY);
                        });
                try {
                    TaskSkill taskSkill = TaskSkill.builder()
                            .categoryBig(abilityReq.getCategoryBig())
                            .categorySmall(abilityReq.getCategorySmall())
                            .rating(abilityReq.getRating())
                            .irumi(irumi)
                            .build();
                    abilityEntity = taskSkillRepository.save(taskSkill);
                } catch (Exception exception) {
                    throw new BaseException(DB_CONNECTION_POOL_ERROR);
                }
                break;
            case "soft-skill":
                softSkillRepository.findByIrumiIdAndCategorySmallAndDeletionStatus(irumiId, abilityReq.getCategorySmall(), UNDELETED)
                        .ifPresent((a) -> {
                            throw new BaseException(BAD_REQ_ALREADY_EXIST_ABILITY);
                        });
                try {
                    SoftSkill softSkill = SoftSkill.builder()
                            .categoryBig(abilityReq.getCategoryBig())
                            .categorySmall(abilityReq.getCategorySmall())
                            .rating(abilityReq.getRating())
                            .irumi(irumi)
                            .build();
                    abilityEntity = softSkillRepository.save(softSkill);
                } catch (Exception exception) {
                    throw new BaseException(DB_CONNECTION_POOL_ERROR);
                }
                break;
            case "program-skill":
                programSkillRepository.findByIrumiIdAndCategorySmallAndDeletionStatus(irumiId, abilityReq.getCategorySmall(), UNDELETED)
                        .ifPresent((a) -> {
                            throw new BaseException(BAD_REQ_ALREADY_EXIST_ABILITY);
                        });
                try {
                    ProgramSkill programSkill = ProgramSkill.builder()
                            .categoryBig(abilityReq.getCategoryBig())
                            .categorySmall(abilityReq.getCategorySmall())
                            .rating(abilityReq.getRating())
                            .irumi(irumi)
                            .build();
                    abilityEntity = programSkillRepository.save(programSkill);
                } catch (Exception exception) {
                    throw new BaseException(DB_CONNECTION_POOL_ERROR);
                }
                break;
            default:
                throw new BaseException(BAD_REQ_NOT_ABILITY);
        }
        return new AbilityRes(abilityEntity.getId(), abilityEntity.getCategoryBig(), abilityEntity.getCategorySmall(), abilityEntity.getRating());
    }

    @Transactional(readOnly = true)
    public AbilityListDto getIrumiAbility(Long irumiId, String ability) {
        Irumi irumi = irumiRepository.findByIdAndDeletionStatus(irumiId, UNDELETED)
                .orElseThrow(() -> new BaseException(NOT_FOUND_IRUMI));
        List<? extends Ability> abilityEntityList;
        switch (ability) {
            case "task-field":
                abilityEntityList = taskFieldRepository.findByIrumiIdAndDeletionStatus(irumiId, UNDELETED);
                break;
            case "task-skill":
                abilityEntityList = taskSkillRepository.findByIrumiIdAndDeletionStatus(irumiId, UNDELETED);
                break;
            case "soft-skill":
                abilityEntityList = softSkillRepository.findByIrumiIdAndDeletionStatus(irumiId, UNDELETED);
                break;
            case "program-skill":
                abilityEntityList = programSkillRepository.findByIrumiIdAndDeletionStatus(irumiId, UNDELETED);
                break;
            default:
                throw new BaseException(BAD_REQ_NOT_ABILITY);
        }

        List<AbilityRes> abilityResList = abilityEntityList.stream()
                .map(ability_ -> new AbilityRes(
                        ability_.getId(),
                        ability_.getCategoryBig(),
                        ability_.getCategorySmall(),
                        ability_.getRating())
                ).collect(Collectors.toList());

        return new AbilityListDto(irumiId, abilityResList.size(), abilityResList);
    }

    public void deleteIrumiAbility(Long irumiId, String ability, Long abilityId) {
        irumiRepository.findByIdAndDeletionStatus(irumiId, UNDELETED)
                .orElseThrow(() -> new BaseException(NOT_FOUND_IRUMI));
        switch (ability) {
            case "task-field":
                TaskField taskField = taskFieldRepository.findByIdAndDeletionStatus(abilityId, UNDELETED)
                        .orElseThrow(() -> new BaseException(NOT_FOUND_ABILITY));
                taskField.deleteEntity();
                break;
            case "task-skill":
                TaskSkill taskSkill = taskSkillRepository.findByIdAndDeletionStatus(abilityId, UNDELETED)
                        .orElseThrow(() -> new BaseException(NOT_FOUND_ABILITY));
                taskSkill.deleteEntity();
                break;
            case "soft-skill":
                SoftSkill softSkill = softSkillRepository.findByIdAndDeletionStatus(abilityId, UNDELETED)
                        .orElseThrow(() -> new BaseException(NOT_FOUND_ABILITY));
                softSkill.deleteEntity();
                break;
            case "program-skill":
                ProgramSkill programSkill = programSkillRepository.findByIdAndDeletionStatus(abilityId, UNDELETED)
                        .orElseThrow(() -> new BaseException(NOT_FOUND_ABILITY));
                programSkill.deleteEntity();
                break;
            default:
                throw new BaseException(BAD_REQ_NOT_ABILITY);
        }
    }
}

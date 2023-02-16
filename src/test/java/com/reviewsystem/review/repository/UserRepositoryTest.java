package com.reviewsystem.review.repository;

import com.reviewsystem.review.user.entity.user.User;
import com.reviewsystem.review.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static com.reviewsystem.review.global.entity.BaseEntity.DeletionStatus.UNDELETED;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional
//    @Rollback(value = false)
    public void userTest() throws Exception {
        //given
        User user = User.builder()
                .email("email@naver.com")
                .password("password1")
                .phone("010-4023-6039")
                .serviceAgreement(true)
                .personalDataAgreement(true)
                .marketingAgreement(null)
                .build();


        //when
        User findUser = userRepository.findByIdAndDeletionStatus(user.getId(), UNDELETED).get();

        //then
        System.out.println("user.getId() = " + user.getId());
        System.out.println("findUser.getId() = " + findUser.getId());

//        Assertions.assertEquals(user.getId(), findUser.getId());
//        Assertions.assertEquals(user.getEmail(), findUser.getEmail());
//        Assertions.assertEquals(findUser, user);
//
//        System.out.println("findUser == user = " + (findUser == user));
    }

}

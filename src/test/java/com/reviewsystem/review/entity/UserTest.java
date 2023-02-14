package com.reviewsystem.review.entity;

import com.reviewsystem.review.user.entity.User;
import com.reviewsystem.review.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserTest {
    @Test
    public void userTest() throws Exception {
        //given
        User user = User.builder()
                .email("email@naver.com")
                .password("password1")
                .phone("01011201001")
                .state("boston")
                .build();

        //when

        //then
        System.out.println("user.toString() = " + user.toString());
    }
}

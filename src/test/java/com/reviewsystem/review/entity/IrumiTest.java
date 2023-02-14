package com.reviewsystem.review.entity;

import com.reviewsystem.review.user.entity.Irumi;
import com.reviewsystem.review.user.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class IrumiTest {
    @Test
    public void IrumiTesting() throws Exception {
        //given
        User user = User.builder()
                .email("email@naver.com")
                .password("password1")
                .phone("01011201001")
                .state("boston")
                .build();

        Irumi irumi = Irumi.builder()
                .finalDegree("중졸")
                .major("전자회뢰")
                .job("무직")
                .build();
        //when

        //then
        System.out.println("irumi.toString() = " + irumi.toString());
    }
}

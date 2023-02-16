package com.reviewsystem.review.user;

import com.reviewsystem.review.global.Response.ResponseDto;
import com.reviewsystem.review.user.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/review-system/users")
public class UserController {
    private final UserService userService;

    @ResponseBody
    @PostMapping("/sign-up")
    public ResponseDto<PostUserRes> createUser(
            @Validated @RequestBody PostUserReq postUserReq, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseDto<>(bindingResult);
        }
        PostUserRes postUserRes = userService.createUser(postUserReq);
        return new ResponseDto<>(postUserRes);
    }

    @ResponseBody
    @GetMapping("/{userId}")
    public ResponseDto<GetUserRes> getUserById(@PathVariable("userId") Long userId) {
        GetUserRes getUserRes = userService.getUserById(userId);
        return new ResponseDto<>(getUserRes);
    }

    @ResponseBody
    @PatchMapping("/{userId}/info")
    public ResponseDto<String> updateUserInfo(
            @PathVariable("userId") Long userId,
            @Validated @RequestBody PatchUserInfoReq patchUserInfoReq,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseDto<>(bindingResult);
        }
        userService.updateUserInfo(userId, patchUserInfoReq);

        return new ResponseDto<String>("유저 정보가 수정되었습니다.");
    }

    @ResponseBody
    @PatchMapping("/{userId}/password")
    public ResponseDto<String> updateUserPassword(
            @PathVariable("userId") Long userId,
            @Validated @RequestBody PatchUserPasswordReq patchUserPasswordReq,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseDto<>(bindingResult);
        }
        userService.updateUserPassword(userId, patchUserPasswordReq);

        return new ResponseDto<String>("비밀번호가 변경되었습니다.");
    }

    @ResponseBody
    @DeleteMapping("/{userId}/delete")
    public ResponseDto<String> deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);

        return new ResponseDto<String>("회원 탈퇴가 완료 되었습니다.");
    }
}

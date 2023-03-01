package com.reviewsystem.review.user;

import com.reviewsystem.review.global.Response.ResponseDto;
import com.reviewsystem.review.user.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
            @Validated @RequestBody PostUserReq postUserReq) {
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
            @Validated @RequestBody PatchUserInfoReq patchUserInfoReq) {
        userService.updateUserInfo(userId, patchUserInfoReq);

        return new ResponseDto<String>("유저 정보가 수정되었습니다.");
    }

    @ResponseBody
    @PatchMapping("/{userId}/password")
    public ResponseDto<String> updateUserPassword(
            @PathVariable("userId") Long userId,
            @Validated @RequestBody PatchUserPasswordReq patchUserPasswordReq) {
        userService.updateUserPassword(userId, patchUserPasswordReq);
        return new ResponseDto<String>("비밀번호가 변경되었습니다.");
    }

    @ResponseBody
    @DeleteMapping("/{userId}/delete")
    public ResponseDto<String> deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
        return new ResponseDto<String>("회원 탈퇴가 완료 되었습니다.");
    }

    @ResponseBody
    @PostMapping("/{userId}/irumi/profile")
    public ResponseDto<PostIrumiRes> createIrumiProfile(
            @PathVariable("userId") Long userId,
            @Validated @RequestBody PostIrumiReq postIrumiReq) {
        PostIrumiRes postIrumiRes = userService.createIrumiProfile(userId, postIrumiReq);
        return new ResponseDto<>(postIrumiRes);
    }

    @ResponseBody
    @GetMapping("irumies/{irumiId}/profile")
    public ResponseDto<GetIrumiRes> getIrumiProfile(@PathVariable("irumiId") Long irumiId) {
        GetIrumiRes getIrumiRes = userService.getIrumiProfile(irumiId);
        return new ResponseDto<GetIrumiRes>(getIrumiRes);
    }

    @ResponseBody
    @PatchMapping("irumies/{irumiId}/profile")
    public ResponseDto<String> updateIrumiProfile(
            @PathVariable("irumiId") Long irumiId, @RequestBody PatchIrumiReq patchIrumiReq) {
        userService.updateIrumiProfile(irumiId, patchIrumiReq);
        return new ResponseDto<String>("이루미 정보 수정에 성공하였습니다.");
    }

    @ResponseBody
    @PostMapping("/irumies/{irumiId}/{ability}")
    public ResponseDto<AbilityRes> createIrumiAbility(
            @PathVariable("irumiId") Long irumiId, @PathVariable("ability") String ability,
            @Validated @RequestBody AbilityReq abilityReq) {
        AbilityRes abilityRes = userService.createIrumiAbility(irumiId, ability.toLowerCase(), abilityReq);
        return new ResponseDto<>(abilityRes);
    }

    @ResponseBody
    @GetMapping("/irumies/{irumiId}/{ability}")
    public ResponseDto<AbilityListDto> getIrumiAbility(
            @PathVariable("irumiId") Long irumiId, @PathVariable("ability") String ability){
        AbilityListDto abilityList = userService.getIrumiAbility(irumiId, ability.toLowerCase());
        return new ResponseDto<>(abilityList);
    }

    @ResponseBody
    @DeleteMapping("/irumies/{irumiId}/{ability}/{abilityId}")
    public ResponseDto<String> createIrumiTaskField(
            @PathVariable("irumiId") Long irumiId, @PathVariable("ability") String ability,
            @PathVariable("abilityId") Long abilityId) {
        userService.deleteIrumiAbility(irumiId, ability.toLowerCase(), abilityId);

        return new ResponseDto<>("역량이 삭제 되었습니다.");
    }
}

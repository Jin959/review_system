package com.reviewsystem.review.global.Response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResponseHttpStatus {

    // 200
    SUCCESS(HttpStatus.OK, "요청에 성공했습니다."),

    // 400
    BAD_REQ_ENTITY_VALIDATION(HttpStatus.BAD_REQUEST, "전달한 파라미터가 형식에 맞지 않습니다. 빠진 값이나 타입을 확인해주세요."),
    BAD_REQ_USER_EXISTS_EMAIL(HttpStatus.BAD_REQUEST, "해당 이메일로 이미 가입된 계정이 존재합니다."),

    // 404
    NOT_FOUND(HttpStatus.NOT_FOUND, "해당 되는 정보가 존재하지 않습니다."),
    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "해당 정보와 일치하는 유저가 존재하지 않습니다."),
    NOT_FOUND_USER_ALREADY_DELETE(HttpStatus.NOT_FOUND, "이미 탈퇴한 계정입니다."),

    // 500
    DB_CONNECTION_POOL_ERROR(HttpStatus.NOT_FOUND, "데이터 베이스 연동에 실패 했습니다."),
    UNEXPECTED_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "예상하지 못한 에러가 발생했습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    private ResponseHttpStatus(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}

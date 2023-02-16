package com.reviewsystem.review.global.exception;

import com.reviewsystem.review.global.Response.ResponseHttpStatus;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {
    private ResponseHttpStatus responseHttpStatus;

    public BaseException(ResponseHttpStatus responseHttpStatus) {
        super(responseHttpStatus.getMessage());
        this.responseHttpStatus = responseHttpStatus;
    }
}

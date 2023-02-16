package com.reviewsystem.review.global.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import static com.reviewsystem.review.global.Response.ResponseHttpStatus.SUCCESS;

@Getter
@AllArgsConstructor
public class ResponseDto<T> {
    private int code;
    private String type;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public ResponseDto(T data) {
        this.code = SUCCESS.getHttpStatus().value();
        this.type = SUCCESS.getHttpStatus().getReasonPhrase();
        this.message = SUCCESS.getMessage();
        this.data = data;
    }

    public ResponseDto(ResponseHttpStatus resCode) {
        this.code = resCode.getHttpStatus().value();
        this.type = resCode.getHttpStatus().getReasonPhrase();
        this.message = resCode.getMessage();
    }

    public ResponseDto(BindingResult bindingResult) {
        FieldError error = bindingResult.getFieldError();
        this.code = HttpStatus.BAD_REQUEST.value();
        this.type = HttpStatus.BAD_REQUEST.getReasonPhrase();
        this.message = error.getDefaultMessage();
    }
}

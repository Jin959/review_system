package com.reviewsystem.review.global.exception;

import com.reviewsystem.review.global.Response.ResponseDto;
import com.reviewsystem.review.global.Response.ResponseHttpStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

import static com.reviewsystem.review.global.Response.ResponseHttpStatus.*;

@Slf4j
@RestControllerAdvice
public class GlobalHandler {
    @ExceptionHandler(BaseException.class)
    public ResponseDto<ResponseHttpStatus> BaseExceptionHandler(BaseException exception) {
        log.warn("BaseExceptionHandler caught!!: {}", exception.getMessage());
        log.warn("BaseException : ", exception);
        return new ResponseDto<>(exception.getResponseHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseDto<ResponseHttpStatus> ExceptionHandler(Exception exception) {
        log.error("ExceptionHandler caught!! : ", exception);
        return new ResponseDto<>(UNEXPECTED_EXCEPTION);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseDto<ResponseHttpStatus> ConstraintViolationExceptionHandler(ConstraintViolationException exception) {
        log.warn("ConstraintViolationExceptionHandler caught!!: {}", exception.getMessage());
        log.warn("ConstraintViolationException : ", exception);
        return new ResponseDto<>(BAD_REQ_ENTITY_VALIDATION);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseDto<String> validationExceptionHandler(MethodArgumentNotValidException exception) {
        log.warn("MethodArgumentNotValidException caught!!: {}", exception.getMessage());
        log.warn("MethodArgumentNotValidException : ", exception);
        BindingResult bindingResult = exception.getBindingResult();
        return new ResponseDto<>(bindingResult);
    }

}

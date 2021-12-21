package com.fanbakery.fancake.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@ControllerAdvice(annotations = RestController.class)
public class ApiSvcExceptionHandler {

    /*
     * 서비스 오류에 대한 전역 처리
     */
    @ExceptionHandler(value = {ApiServiceException.class})
    @ResponseBody
    public ResponseEntity<?> apiServiceException(ApiServiceException ex) {
        return  ResponseEntity.internalServerError().body(ex);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exception(Exception e) {
        log.error("", e);
        return ResponseEntity.internalServerError().body(new ApiServiceException(ApiErrorCode.ERR_ETC_INTERNAL, "기타 서버에러"));
    }


    /*
     * Validation 에 대한 전역 처리
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        BindingResult bindingResult = e.getBindingResult();
        StringBuilder builder = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            builder.append(fieldError.getField());
            builder.append(" = ");
            builder.append(fieldError.getDefaultMessage());
        }

        return ResponseEntity.internalServerError().body(new ApiServiceException(ApiErrorCode.ERR_INVALID_PARAM, builder.toString()));
    }


    @ExceptionHandler(BindException.class)
    public Object handleBindException(BindException e) {

        BindingResult bindingResult = e.getBindingResult();
        StringBuilder builder = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            builder.append(fieldError.getField());
            builder.append(" = ");
            builder.append(fieldError.getDefaultMessage());
        }
        return ResponseEntity.internalServerError().body(new ApiServiceException(ApiErrorCode.ERR_INVALID_PARAM, builder.toString()));
    }


}

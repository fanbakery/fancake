package com.fanbakery.fancake.exception;

import lombok.Data;

@Data
public class ApiServiceException extends RuntimeException{

    private ApiErrorCode errCode;
    private String errMsg;

    public ApiServiceException( ApiErrorCode errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }
}

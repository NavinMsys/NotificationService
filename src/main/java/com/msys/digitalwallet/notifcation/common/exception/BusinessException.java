package com.msys.digitalwallet.notifcation.common.exception;


import com.msys.digitalwallet.notifcation.enums.ErrorType;

public class BusinessException extends RuntimeException {

    private ErrorType errorType;

    public BusinessException(ErrorType errorType, String message) {
        super(message);
        this.errorType = errorType;
    }

    public BusinessException(BusinessException e) {
        super(e.getMessage());
        this.errorType = e.getErrorType();
    }

    public ErrorType getErrorType() {
        return this.errorType;
    }
}

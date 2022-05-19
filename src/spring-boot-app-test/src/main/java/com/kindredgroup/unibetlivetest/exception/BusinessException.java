package com.kindredgroup.unibetlivetest.exception;

import com.kindredgroup.unibetlivetest.types.BusinessExceptionType;
import lombok.Data;

@Data
public class BusinessException extends RuntimeException {

    private final BusinessExceptionType exception;
    private final String message;

    public BusinessException(String message, BusinessExceptionType exception) {
        this.message = message;
        this.exception = exception;
    }

}

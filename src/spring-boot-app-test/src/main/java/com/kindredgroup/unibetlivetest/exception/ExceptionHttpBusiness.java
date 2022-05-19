package com.kindredgroup.unibetlivetest.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class ExceptionHttpBusiness {

    @ExceptionHandler(BusinessException.class)
    public String handleBusinessException(BusinessException ex, HttpServletResponse response) {
        try {
            response.setStatus(ex.getException().getBusinessCodeError(), ex.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ex.getMessage();
    }
}

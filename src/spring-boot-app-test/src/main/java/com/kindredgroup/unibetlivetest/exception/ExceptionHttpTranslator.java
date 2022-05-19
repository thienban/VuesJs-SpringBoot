package com.kindredgroup.unibetlivetest.exception;

import com.kindredgroup.unibetlivetest.dto.ExceptionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
@RequestMapping(produces = "application/vnd.error+json")
public class ExceptionHttpTranslator {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity businessException(HttpServletRequest request, final CustomException e) {
        return new ResponseEntity(new ExceptionDto().setErrormessage(e.getMessage())
                .setPath(request.getServletPath()), e.getException().getStatus());
    }

    @ExceptionHandler(BusinessException.class)
    public String handleBusinessException(BusinessException ex, HttpServletResponse response) {
        try {
            response.sendError(ex.getException().getBusinessCodeError());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ex.getMessage();
    }

}

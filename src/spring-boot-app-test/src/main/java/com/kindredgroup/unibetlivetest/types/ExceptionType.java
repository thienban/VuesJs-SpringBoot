package com.kindredgroup.unibetlivetest.types;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public enum ExceptionType {

    CUSTOMER_NOT_FOUND(HttpStatus.NOT_FOUND),
    SELECTION_NOT_FOUND(HttpStatus.NOT_FOUND),
    EXISTED_BET(HttpStatus.CONFLICT);

    @Getter
    final HttpStatus status;

    ExceptionType(HttpStatus status) {
        this.status = status;
    }

}

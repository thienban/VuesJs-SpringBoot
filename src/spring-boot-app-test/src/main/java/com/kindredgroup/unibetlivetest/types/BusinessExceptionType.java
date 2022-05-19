package com.kindredgroup.unibetlivetest.types;

import lombok.Getter;

public enum BusinessExceptionType {

    INSUFFICIENT_BALANCE(600),
    ODD_HAVE_CHANGED(601),
    CLOSED_SELECTION(602);

    @Getter
    final Integer businessCodeError;

    BusinessExceptionType(Integer businessCodeError) {
        this.businessCodeError = businessCodeError;
    }

}

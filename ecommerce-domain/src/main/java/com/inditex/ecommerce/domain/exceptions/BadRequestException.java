package com.inditex.ecommerce.domain.exceptions;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String detail) {
        super(detail);
    }

}

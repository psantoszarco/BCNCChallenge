package com.inditex.ecommerce.domain.exceptions;

public class ConflictException extends RuntimeException {

    public ConflictException(String detail) {
        super(detail);
    }

}

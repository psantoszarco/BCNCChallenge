package com.inditex.ecommerce.rest_web.controlleradvice;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {
    private int httpStatus;

    private String type, message, clazz, method;

    private int line;
}

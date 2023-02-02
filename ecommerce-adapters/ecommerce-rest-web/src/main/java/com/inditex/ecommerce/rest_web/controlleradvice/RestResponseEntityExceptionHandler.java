package com.inditex.ecommerce.rest_web.controlleradvice;

import com.inditex.ecommerce.domain.exceptions.ConflictException;
import com.inditex.ecommerce.domain.exceptions.PriceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PriceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(Exception ex) {
        return new ResponseEntity<>(getErrorResponse(ex, HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({
            ConflictException.class,
            ConstraintViolationException.class
    })
    public ResponseEntity<ErrorResponse> handleConflictException(Exception ex) {
        return new ResponseEntity<>(getErrorResponse(ex, HttpStatus.CONFLICT), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> handleInternalServerErrorException(Exception ex) {
        return new ResponseEntity<>(getErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ErrorResponse getErrorResponse(Exception ex, HttpStatus httpStatus) {
        String causeMessage = ex.getMessage();
        int httpCode = httpStatus.value();
        String httpName = httpStatus.name();
        String clazz = getClassName(ex);
        String method = ex.getStackTrace().length > 0 ? ex.getStackTrace()[0].getMethodName() : "Not found";
        int line = ex.getStackTrace().length > 0 ? ex.getStackTrace()[0].getLineNumber() : 0;

        return ErrorResponse.builder()
                .clazz(clazz)
                .httpStatus(httpCode)
                .message(causeMessage)
                .method(method)
                .type(httpName)
                .line(line)
                .build();
    }

    private String getClassName(Exception ex) {
        String className = "Not found";

        try {
            className = Class.forName(ex.getStackTrace()[0].getClassName()).getName();
        } catch (Exception ignored) {
        }

        return className;
    }
}

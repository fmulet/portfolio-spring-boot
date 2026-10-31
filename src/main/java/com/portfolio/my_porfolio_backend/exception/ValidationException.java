package com.portfolio.my_porfolio_backend.exception;

import org.springframework.validation.BindingResult;

import lombok.Getter;

/**
 * ValidationException
 */
@Getter
public class ValidationException extends RuntimeException {
    private final BindingResult bindingResult;

    // Constructor manual que permite llamar a super()
    public ValidationException(BindingResult bindingResult) {
        super("Validation error: " + bindingResult.getErrorCount()); // Llama al constructor de RuntimeException
        this.bindingResult = bindingResult;
    }

}

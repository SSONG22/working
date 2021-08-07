package com.songi.working.result.error;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class ErrorResponse {
    private String message;
    private int status;
    private List<FieldError> errors;

    private ErrorResponse(final ErrorCode code, final List<FieldError> errors) {
        this.message = code.getMessage();
        this.status = code.getStatus();
        this.errors = errors;
    }

    private ErrorResponse(final ErrorCode code) {
        this.message = code.getMessage();
        this.status = code.getStatus();
        this.errors = new ArrayList<>();
    }

    private ErrorResponse(final ErrorCode code, final String message) {
        this.message = message;
        this.status = code.getStatus();
        this.errors = new ArrayList<>();
    }

    public static ErrorResponse of(final ErrorCode code, BindingResult bindingResult) {
        return new ErrorResponse(code, bindingResult.getFieldErrors()
                .stream()
                .map(fieldError ->
                        new FieldError(fieldError.getField(),
                                fieldError.getRejectedValue() == null ? "" : fieldError.getRejectedValue().toString(),
                                fieldError.getDefaultMessage()))
                .collect(Collectors.toList()));
    }

    public static ErrorResponse of(final ErrorCode code) {
        return new ErrorResponse(code);
    }

    public static ErrorResponse of(MethodArgumentTypeMismatchException e) {
        final String value = e.getValue() == null ? "" : e.getValue().toString();
        return new ErrorResponse(ErrorCode.INVALID_INPUT_VALUE, new ArrayList<>((Collection<? extends FieldError>) new FieldError(e.getName(), value, e.getErrorCode())));
    }

    public static ErrorResponse of(final ErrorCode code, final String message) {
        return new ErrorResponse(code, message);
    }

}

package com.datePage.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class DatePageException extends RuntimeException {

    public final Map<String, String> validation = new HashMap<>();

    public DatePageException(String message) {
        super(message);
    }

    public DatePageException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int statusCode();

    public void addValidation(String fieldName, String message) {
        validation.put(fieldName, message);
    }
}

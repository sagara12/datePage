package com.datePage.exception;

import lombok.Getter;

// status: 400
@Getter
public class InvalidRequest extends DatePageException {

    private static final String MESSAGE  = "잘못된 요청 입니다.";

    public String fieldName;
    public String message;



    public InvalidRequest() {
        super(MESSAGE);
        addValidation(fieldName, message);
    }

    public InvalidRequest(String fieldName, String message) {
        super(MESSAGE);
        this.fieldName = fieldName;
        this.message = message;
    }

    @Override
    public int statusCode() {
        return 400;
    }

}

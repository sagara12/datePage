package com.datePage.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 *  {
 *         "code" : "400"
 *         "message" : "잘못된 요청입니다"
 *         "validation" :"{
 *
 *         "id": 값을 입력해주세요
 *
 *         }
 *
 *  }
 */

@Getter
//@JsonInclude(value = JsonInclude.Include.NON_EMPTY) // 비어있는 데이터는 표시 안함
public class ErrorResponse {

    private final String code;
    private final String message;
    private final Map<String, String> validation;

    @Builder
    public ErrorResponse(String code, String message, Map<String, String> validation) {
        this.code = code;
        this.message = message;
        this.validation = validation;
    }

    public void addValidation(String field, String errorMassage) {
        this.validation.put(field,errorMassage);
    }
}

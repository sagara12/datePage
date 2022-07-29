package com.datePage.datePage.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class PostCreate {

    @NotBlank(message = "Id를 입력해 주세요")
    private String id;

    @NotBlank(message = "password를 입력해 주세요")
    private String password;

    @Builder //-> 자바 빌더 패턴
    public PostCreate(String id, String password) {
        this.id = id;
        this.password = password;
    }
}

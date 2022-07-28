package com.datePage.datePage.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Data
public class PostCreate {

    @NotBlank(message = "Id를 입력해 주세요")
    private String id;

    @NotBlank(message = "password를 입력해 주세요")
    private String password;


}

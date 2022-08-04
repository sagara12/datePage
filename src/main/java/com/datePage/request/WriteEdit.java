package com.datePage.request;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
@ToString
public class WriteEdit {

    @NotBlank(message = "타이틀을 입력해주세요")
    private String title;

    @NotBlank(message = "내용을 입력해주세요")
    private String content;

    @Builder
    public WriteEdit (String title, String content) {
        this.title = title;
        this.content = content;
    }

}

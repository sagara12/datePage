package com.datePage.request;

import com.datePage.exception.InvalidRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class WriteCreate {

    @NotBlank(message = "타이틀을 입력해주세요")
    private String title;

    @NotBlank(message = "내용을 입력해주세요")
    private String content;

    @Builder
    public WriteCreate(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void validate() {
        if (title. contains("바보")){
            throw  new InvalidRequest("title", "제목에 바보를 포함할 수 없습니다.");
        }

    }
}

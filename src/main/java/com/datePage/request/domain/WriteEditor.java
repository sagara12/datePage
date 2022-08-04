package com.datePage.request.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

@Getter
@NoArgsConstructor
public class WriteEditor {

    private String title;

    private String content;

    @Builder
    public WriteEditor(String title, String content) {
        this.title = title;
        this.content = content;
    }
}

package com.datePage.request.domain;

import com.datePage.request.WriteEdit;
import lombok.*;

import javax.persistence.*;


@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class Write {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long writeId;

    @Column(name = "write_title")
    private String title;

    @Lob
    private String content;

    @Builder
    public Write(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public WriteEditor.WriteEditorBuilder toEditor() {
       return WriteEditor.builder()
                .title(title)
                .content(content);

    }

    public void edit(WriteEditor writeEditor) {
        title = writeEditor.getTitle();
        content = writeEditor.getContent();
    }
}
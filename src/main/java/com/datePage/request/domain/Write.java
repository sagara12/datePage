package com.datePage.request.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class Write {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

}
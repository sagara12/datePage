package com.datePage.request.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Table(name = "Document_Write")
public class Write {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "write_number")
    private Long write_id;

    @Column(name = "write_title")
    private String title;

    @Column(name = "write_content")
    private String content;

    @Builder
    public Write(String title, String content) {
        this.title = title;
        this.content = content;
    }

}

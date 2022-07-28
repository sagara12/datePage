package com.datePage.datePage.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class Post {

    @Id
    private String id;

    private String password;

    public Post(String id, String password) {
        this.id = id;
        this.password = password;
    }
}

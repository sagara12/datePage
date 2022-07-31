package com.datePage.request.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class Post {

    @Id
    @Column(name = "private_id")
    private String id;

    @Column(name = "private_password")
    private String password;

    @Builder
    public Post(String id, String password) {
        this.id = id;
        this.password = password;
    }

    /*public PostCreate changeTitle(String id, String password){
        return PostCreate.builder()
                .id(id)
                .password(password)
                .build();
    }*/

}

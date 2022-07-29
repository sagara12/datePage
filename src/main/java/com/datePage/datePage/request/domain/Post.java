package com.datePage.datePage.request.domain;

import com.datePage.datePage.request.PostCreate;
import lombok.AccessLevel;
import lombok.Builder;
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

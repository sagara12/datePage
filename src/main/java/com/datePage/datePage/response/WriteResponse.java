package com.datePage.datePage.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *
 */

@Getter
public class WriteResponse {

    private Long write_id;

    private String title;

    private String content;

    /*public String getTitle() {
        return this.title.substring(0,10);
    }*/

    @Builder
    public WriteResponse(Long write_id, String title, String content) {
        this.write_id = write_id;
        this.title = title.substring(0,Math.min(title.length(), 10));
        this.content = content;
    }
}

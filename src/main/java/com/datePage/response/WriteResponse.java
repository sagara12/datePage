package com.datePage.response;

import com.datePage.request.domain.Write;
import lombok.Builder;
import lombok.Getter;

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

    //생성자 오버로딩
    public WriteResponse (Write write) {

        this.write_id = write.getWriteId();
        this.title = write.getTitle();
        this.content  = write.getContent();

    }


    @Builder
    public WriteResponse(Long write_id, String title, String content) {
        this.write_id = write_id;
        this.title = title.substring(0,Math.min(title.length(), 10));
        this.content = content;
    }
}

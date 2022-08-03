package com.datePage.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WriteSearch {

    private static final int MAX = 2000;

    @Builder.Default
    private Integer page =1;
    @Builder.Default
    private Integer size =10;

   /*
    public WriteSearch( Integer page, Integer size) {
        *//*this.page = page == null ? 1:page;
        this.size = size == null ? 20:size;*//*
        this.page = page;
        this.size = size;
    }*/

    public long getOffset() {
        return (long) (Math.max(1, page)-1) * Math.min(size,2000);
    }
}

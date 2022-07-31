package com.datePage.datePage.service;

import com.datePage.datePage.repository.WriteRepository;
import com.datePage.datePage.request.PostCreate;
import com.datePage.datePage.request.WriteCreate;
import com.datePage.datePage.request.domain.Post;
import com.datePage.datePage.request.domain.Write;
import com.datePage.datePage.response.WriteResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WriteServiceTest {

    @Autowired
    private WriteService writeService;

    @Autowired
    private WriteRepository writeRepository;


    @BeforeEach
    void clean() {
        writeRepository.deleteAll();
    }

    @Test
    @DisplayName("글 작성")
    void test() {

        //given
        WriteCreate writeCreate = WriteCreate.builder()
                .title("제목입니다")
                .content("내용입니다")
                .build();



        //when
        writeService.write(writeCreate);


        //then
        Assertions.assertEquals(1L, writeRepository.count());
        Write write = writeRepository.findAll().get(0);
        assertEquals("제목입니다", write.getTitle());
        assertEquals("내용입니다", write.getContent());
    }

    @Test
    @DisplayName("글 1개 조회")
    void test2() {
        //given
        Write requestWrite = Write.builder()
                .title("title")
                .content("content")
                .build();

        writeRepository.save(requestWrite);


        // 클라언트 요구사항
            // json응답에서 title값 길이를 최대 10글자로 해주세요.


        Long writeId = 1L;

        //when
        WriteResponse writeResponse  = writeService.get(requestWrite.getWrite_id());

        //then
        assertNotNull(writeResponse);
        assertEquals(1L, writeRepository.count());
        assertEquals("title", writeResponse.getTitle());
        assertEquals("content", writeResponse.getContent());

    }
}
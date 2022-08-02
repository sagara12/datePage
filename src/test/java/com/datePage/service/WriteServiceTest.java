package com.datePage.service;

import com.datePage.repository.WriteRepository;
import com.datePage.request.WriteCreate;
import com.datePage.request.domain.Write;
import com.datePage.response.WriteResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        WriteResponse writeResponse  = writeService.get(requestWrite.getWriteId());

        //then
        assertNotNull(writeResponse);
        assertEquals(1L, writeRepository.count());
        assertEquals("title", writeResponse.getTitle());
        assertEquals("content", writeResponse.getContent());

    }

    //글이 너무 많은 경우에 -> 비용이 너무 많이 든다


    @Test
    @DisplayName("글 1페이지 조회")
    void test3() {
        //given
        List<Write> requestWrite = IntStream.range(1, 31)
                        .mapToObj( i -> {
                            return Write.builder()
                                    .title("글 제목 " + i)
                                    .content("글 내용 " + i)
                                    .build();
                        })
                                .collect(Collectors.toList());

        writeRepository.saveAll(requestWrite);

        Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "writeId");

        //when
        List<WriteResponse> writes = writeService.getList(pageable);

        //then
        assertEquals(5L, writes.size());
        assertEquals("글 제목 30" , writes.get(0).getTitle());
        assertEquals("글 제목 26" , writes.get(4).getTitle());


    }
}
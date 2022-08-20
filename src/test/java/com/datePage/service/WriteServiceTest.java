package com.datePage.service;

import com.datePage.exception.WriteNotFound;
import com.datePage.repository.WriteRepository;
import com.datePage.request.WriteCreate;
import com.datePage.request.WriteEdit;
import com.datePage.request.WriteSearch;
import com.datePage.request.domain.Write;
import com.datePage.response.WriteResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        List<Write> requestWrite = IntStream.range(0, 20)
                        .mapToObj( i -> {
                            return Write.builder()
                                    .title("글 제목 " + i)
                                    .content("글 내용 " + i)
                                    .build();
                        })
                                .collect(Collectors.toList());

        writeRepository.saveAll(requestWrite);

        //페이정 처리 1
        /*Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "writeId");*/

        WriteSearch writeSearch = WriteSearch.builder()
                .page(1)
                .build();



        //when
        List<WriteResponse> writes = writeService.getList(writeSearch);

        //then
        assertEquals(10L, writes.size());
        assertEquals("글 제목 19" , writes.get(0).getTitle());

    }
    @Test
    @DisplayName("게시글 제목 수정")
    void test4() {
        //given
        Write write = Write.builder()
                .title("글 제목 O")
                .content("글 내용 O")
                .build();

        writeRepository.save(write);

        WriteEdit  writeEdit = WriteEdit.builder()
                .title("글 제목 수정")
                .content("글 내용 O")
                .build();

        //when
        writeService.edit(write.getWriteId(), writeEdit);

        //then
        Write changedWrite = writeRepository.findById(write.getWriteId())
                .orElseThrow(() -> new RuntimeException("글이 존재 하지 않습니다. id = " + write.getWriteId()));
        Assertions.assertEquals("글 제목 수정", changedWrite.getTitle());
        Assertions.assertEquals("글 내용 O", changedWrite.getContent());
    }

    @Test
    @DisplayName("게시글 내용 수정")
    void test5() {
        //given
        Write write = Write.builder()
                .title("글 제목 O")
                .content("글 내용 O")
                .build();

        writeRepository.save(write);

        WriteEdit  writeEdit = WriteEdit.builder()
                .title("글 제목 O")
                .content("글 내용 수정")
                .build();

        //when
        writeService.edit(write.getWriteId(), writeEdit);

        //then
        Write changedWrite = writeRepository.findById(write.getWriteId())
                .orElseThrow(() -> new RuntimeException("글이 존재 하지 않습니다. id = " + write.getWriteId()));
        Assertions.assertEquals("글 제목 O", changedWrite.getTitle());
        Assertions.assertEquals("글 내용 수정", changedWrite.getContent());
    }

    @Test
    @DisplayName("게시글 삭제")
    void test6() {
        //given
        Write write = Write.builder()
                .title("글 제목 O")
                .content("글 내용 O")
                .build();

        writeRepository.save(write);

        WriteEdit  writeEdit = WriteEdit.builder()
                .title("글 제목 O")
                .content("글 내용 수정")
                .build();

        //when
        writeService.delete(write.getWriteId());

        //then
       Assertions.assertEquals(0,writeRepository.count());
    }

    @Test
    @DisplayName("글 1개 조회")
    void test7() {
        //given
        Write write = Write.builder()
                .title("title11")
                .content("content11")
                .build();

        writeRepository.save(write);


        //post.getId() //primary_id = 1


        Long writeId = 1L;

        //expected
         Assertions.assertThrows(WriteNotFound.class, () ->{
            writeService.get(write.getWriteId() + 1L);
        });


    }

    @Test
    @DisplayName("게시글 삭제 - 존재하지 않는 글")
    void test8() {
        //given
        Write write = Write.builder()
                .title("글 제목 O")
                .content("글 내용 O")
                .build();

        writeRepository.save(write);

        //expected
        Assertions.assertThrows(WriteNotFound.class, () ->{
            writeService.delete(write.getWriteId() + 1L);
        });
    }

    @Test
    @DisplayName("게시글 내용 수정 = 존재하지 않는 글")
    void test9() {
        //given
        Write write = Write.builder()
                .title("글 제목 O")
                .content("글 내용 O")
                .build();

        writeRepository.save(write);

        WriteEdit  writeEdit = WriteEdit.builder()
                .title("글 제목 O")
                .content("글 내용 수정")
                .build();

        //expected
        Assertions.assertThrows(WriteNotFound.class, () ->{
            writeService.edit(write.getWriteId() + 1L, writeEdit);
        });
    }

}
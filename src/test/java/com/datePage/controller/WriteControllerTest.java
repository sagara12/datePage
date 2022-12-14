package com.datePage.controller;

import com.datePage.repository.DataBaseCleaner;
import com.datePage.repository.WriteRepository;
import com.datePage.request.WriteCreate;
import com.datePage.request.WriteEdit;
import com.datePage.request.domain.Write;
import com.datePage.service.DataCleanUp;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.datePage.request.domain.QWrite.write;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@AutoConfigureMockMvc
@SpringBootTest
class WriteControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WriteRepository writeRepository;

    @Autowired
    private DataBaseCleaner dataBaseCleaner;

    @Autowired
    private DataCleanUp dataCleanUp;

    @BeforeEach
    void clean() {
        writeRepository.deleteAll();
        dataCleanUp.execute();
    }


    @Test
    @DisplayName("/write 성공 했습니다.")
    void test() throws Exception {
        //given
        WriteCreate request = WriteCreate.builder()
                .title("제목 입니다")
                .content("내용 입니다")
                .build();

        String json = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/write")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk())
                // .andExpect(MockMvcResultMatchers.content().string("{}")) -> 필요한 상태면 응답을 나중에 구현
                .andDo(MockMvcResultHandlers.print());

        //db-> post 1개 등록
    }

    @Test
    @DisplayName("/write 요청시 title값은 필수다.")
    void test2() throws Exception {
        //

        //given
        WriteCreate request = WriteCreate.builder()
                .content("내용 입니다")
                .build();

        String json = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/write")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                ) //application/json
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
                .andExpect(jsonPath("$.validation.title").value("타이틀을 입력해주세요"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("/write 요청시 content값은 필수다.")
    void test3() throws Exception {
        //given
        WriteCreate request = WriteCreate.builder()
                .title("제목 입니다")
                .build();

        String json = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/write")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                ) //application/json
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
                .andExpect(jsonPath("$.validation.content").value("내용을 입력해주세요"))
                .andDo(MockMvcResultHandlers.print());
    }

    //DB에 test가 저장되는지 확인하는 테스트 작성
    @Test
    @DisplayName("/write 요청시 DB에 값이 저장된다.")
    void test33() throws Exception {
        //given
        WriteCreate request = WriteCreate.builder()
                .title("제목입니다")
                .content("내용입니다")
                .build();

        String json = objectMapper.writeValueAsString(request);

        //when
        mockMvc.perform(post("/write")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        //then
        Assertions.assertEquals(1L, writeRepository.count());

        Write write = writeRepository.findAll().get(0);
        Assertions.assertEquals("제목입니다", write.getTitle());
        Assertions.assertEquals("내용입니다", write.getContent());
    }


    @Test
    @DisplayName("글 1개 조회")
    void test4() throws Exception {
        //given
        Write write = Write.builder()
                    .title("123456789012345")
                    .content("content1")
                    .build();

        writeRepository.save(write);

        //expected
        mockMvc.perform(get("/write/{writeIndex}", write.getWriteId())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.writeId").value(write.getWriteId()))
                .andExpect(jsonPath("$.title").value("1234567890"))
                .andExpect(jsonPath("$.content").value("content1"))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    @DisplayName("글 여러개 조회 & 페이징 처리")
    void test5() throws Exception {

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


        //expected

        mockMvc.perform(get("/writes?page=1&size=10") // /write?page=1&sort=writeId,desc&size=5
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(jsonPath("$.length()", Is.is(10)))
                                    .andExpect(jsonPath("$.[0]writeId").value(30))
                .andExpect(jsonPath("$.[0]title").value("글 제목 30"))
                .andExpect(jsonPath("$.[0]content").value("글 내용 30"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        dataBaseCleaner.cleanWriteDBDataBase();
    }

    @Test
    @DisplayName("글 제목 수정")
    void test6() throws Exception {

        //given
        Write write = Write.builder()
                .title("글 제목 O")
                .content("글 내용 O")
                .build();

        writeRepository.save(write);

        WriteEdit writeEdit = WriteEdit.builder()
                .title("글 제목 수정")
                .content("글 내용 O")
                .build();

        //expected

        mockMvc.perform(patch("/writes/{writeId}", write.getWriteId()) // PATCH /writes/{writeId}
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(writeEdit))
                )
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        dataBaseCleaner.cleanWriteDBDataBase();
    }



    @Test
    @DisplayName("글 제목 삭제")
    void test7() throws Exception {

        //given
        Write write = Write.builder()
                .title("글 제목 O")
                .content("글 내용 O")
                .build();

        writeRepository.save(write);


        //expected

        mockMvc.perform(delete("/writes/{writeId}", write.getWriteId()) // PATCH /writes/{writeId}
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }
    @Test
    @DisplayName("존재하지 않는 게시글 조회")
    void test9() throws Exception {
        //expected
        mockMvc.perform(delete("/writes/{writeId}", 1L) // PATCH /writes/{writeId}
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound())
                .andDo(MockMvcResultHandlers.print());


    }

    @Test
    @DisplayName("존재하지 않는 게시글 수정")
    void test10() throws Exception {
        // given
        WriteEdit writeEdit = WriteEdit.builder()
                .title("글 제목 수정")
                .content("글 내용 O")
                .build();

        // expected
        mockMvc.perform(patch("/writes/{writeId}", 1L) // PATCH /writes/{writeId}
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(writeEdit))
                )
                .andExpect(status().isNotFound())
                .andDo(MockMvcResultHandlers.print());


    }

    @Test
    @DisplayName("게시글 작성시 제목에 바보는 포함 할 수 없다.")
    void test11() throws Exception {
        //given
        WriteCreate request = WriteCreate.builder()
                .title("나는 바보입니다")
                .content("내용입니다.")
                .build();

        String json = objectMapper.writeValueAsString(request);

        //when
        mockMvc.perform(post("/write")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());

    }


}
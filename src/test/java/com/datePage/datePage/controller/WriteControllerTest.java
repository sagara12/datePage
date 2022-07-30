package com.datePage.datePage.controller;

import com.datePage.datePage.repository.PostRepository;
import com.datePage.datePage.repository.WriteRepository;
import com.datePage.datePage.request.PostCreate;
import com.datePage.datePage.request.WriteCreate;
import com.datePage.datePage.request.domain.Write;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

   /* @BeforeEach
    void clean() {
        writeRepository.deleteAll();
    }*/

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
                .andExpect(jsonPath("$.validation.content").value("내용을 입력해주세요"))
                .andDo(MockMvcResultHandlers.print());
    }

    //DB에 test가 저장되는지 확인하는 테스트 작성

    @Test
    @DisplayName("글 1개 조회")
    void test4() throws Exception {
        //given
        Write write = Write.builder()
                    .title("title1")
                    .content("content1")
                    .build();

        writeRepository.save(write);

        //expected
        mockMvc.perform(get("/write/{writeId}", write.getWrite_id())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.write_id").value(write.getWrite_id()))
                .andExpect(jsonPath("$.title").value("title1"))
                .andExpect(jsonPath("$.content").value("content1"))
                .andDo(MockMvcResultHandlers.print());
    }



}
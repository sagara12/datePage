package com.datePage.controller;

import com.datePage.request.WriteCreate;
import com.datePage.response.WriteResponse;
import com.datePage.service.WriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class WriteController {

    private final WriteService writeService;

    @PostMapping("/write")
    public Map post(@RequestBody @Valid WriteCreate request) {
        //post- > 200, 201
        //Case1. 저장한 데이터 Entity -> response로 응답하기
        //Case2. 저장한 데이터 primary_id -> response로 응답하기
        //Case3. 응답 필요 없음 -> response로 응답하기

        //Bad Case:서버에서 반드시 할겁니다->
        Long id = writeService.write(request);
        return Map.of("id",  id);
    }

    /**
     *
     *  /writes -> 글 전체 조회
     *  /writes/{postId} -> 글 한개만 조회
     *
     */

    @GetMapping("/write/{writeId}")
    public WriteResponse get(@PathVariable(name = "writeId") Long id ) {
        WriteResponse writeResponse = writeService.get(id);
        return writeResponse;
    }


    //조회 API
    //여러개의 글을 조회 하는 API
    // /write

    @GetMapping("/write")
    public List<WriteResponse> getList() {
        return writeService.getList();
    }
}
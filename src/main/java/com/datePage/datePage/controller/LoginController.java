package com.datePage.datePage.controller;

import com.datePage.datePage.request.PostCreate;
import com.datePage.datePage.request.domain.Post;
import com.datePage.datePage.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginController {
    //Http
    //GET, POST, PUT, PATCH,  DELETE, OPTIONS, HEAD, TRACE, CONNECT
    //글 등록
    //POST Method

    private final LoginService loginService;

    //@RequestMapping(method = RequestMethod.GET, path = "/v1/posts")
    @PostMapping("/posts")
    public Map post(@RequestBody @Valid PostCreate request) {
        //post- > 200, 201
        //Case1. 저장한 데이터 Entity -> response로 응답하기
        //Case2. 저장한 데이터 primary_id -> response로 응답하기
        //Case3. 응답 필요 없음 -> response로 응답하기

        //Bad Case:서버에서 반드시 할겁니다->
        String id = loginService.write(request);
        return Map.of("id",  id);
    }
}

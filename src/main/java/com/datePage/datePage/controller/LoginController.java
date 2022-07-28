package com.datePage.datePage.controller;

import com.datePage.datePage.domain.Post;
import com.datePage.datePage.request.PostCreate;
import com.datePage.datePage.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
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
    public Map<String, String> post(@RequestBody @Valid PostCreate request) {
        loginService.write(request);
        return Map.of();
    }
}

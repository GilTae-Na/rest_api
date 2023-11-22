package com.ll.rest_api.boundedContext.member.controller;


import com.ll.rest_api.boundedContext.member.entity.Member;
import com.ll.rest_api.boundedContext.member.service.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/member", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class MemberController {

    private final MemberService memberService;

    @Data
    public static class LoginRequest {
        @NotBlank
        private String username;
        @NotBlank
        private String password;
    }


    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse resp) {
        String accessToken = memberService.genAccessToken(loginRequest.getUsername(), loginRequest.getPassword());
        resp.addHeader("Authentication", accessToken);

        return """
                {
                  "resultCode": "S-1",
                  "msg": "엑세스 토큰이 생성되었습니다.",
                  "data": {
                    "accessToken": "%s"
                  }
                }
                """.formatted(accessToken).stripIndent();
    }

}

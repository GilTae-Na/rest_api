package com.ll.rest.boundedContext.member.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {
    @PostMapping("/login")
    public String login() {
        return "성공";
    }
}

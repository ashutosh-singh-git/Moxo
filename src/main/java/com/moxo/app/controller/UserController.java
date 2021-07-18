package com.moxo.app.controller;

import com.moxo.app.dto.BaseResponse;
import com.moxo.app.dto.GenerateCodeDto;
import com.moxo.app.dto.UserCreateDto;
import com.moxo.app.dto.UserInfoResponse;
import com.moxo.app.dto.UserLoginDto;
import com.moxo.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/user/generate")
    public BaseResponse generateCode(@RequestBody GenerateCodeDto codeDto) {
        return userService.generateCode(codeDto);
    }

    @PostMapping(value = "/user/create")
    public UserInfoResponse createUser(@RequestBody UserCreateDto createDto) {
        return userService.createUser(createDto);
    }

    @PostMapping(value = "/user/login")
    public UserInfoResponse loginUser(@RequestBody UserLoginDto loginDto) {
        return userService.login(loginDto);
    }
}

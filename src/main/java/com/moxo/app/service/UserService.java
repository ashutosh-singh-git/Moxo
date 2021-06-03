package com.moxo.app.service;

import com.moxo.app.dto.BaseResponse;
import com.moxo.app.dto.GenerateCodeDto;
import com.moxo.app.dto.UserCreateDto;
import com.moxo.app.dto.UserInfoResponse;
import com.moxo.app.dto.UserLoginDto;

public interface UserService {

    UserInfoResponse login(UserLoginDto userLoginDto);

    BaseResponse generateCode(GenerateCodeDto codeDto);

    UserInfoResponse createUser(UserCreateDto createDto);
}

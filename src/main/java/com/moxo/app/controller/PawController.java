package com.moxo.app.controller;

import com.moxo.app.dto.BaseResponse;
import com.moxo.app.dto.paw.FosterDetails;
import com.moxo.app.dto.paw.LoginUserResponse;
import com.moxo.app.dto.paw.PawConfig;
import com.moxo.app.dto.paw.PawPost;
import com.moxo.app.dto.paw.PawPostResponse;
import com.moxo.app.dto.paw.PawUser;
import com.moxo.app.dto.paw.UserProfile;
import com.moxo.app.entity.paw.PawPageEntity;
import com.moxo.app.service.PawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/paw")
public class PawController {

    @Autowired
    private PawService pawService;

    @GetMapping
    public PawPageEntity fetchLayout(@RequestParam Integer page, @RequestParam Integer size, @RequestParam String pageId) {
        return pawService.getLayout(pageId, page, size);
    }

    @GetMapping("/search")
    public PawPageEntity searchPage(@RequestParam String query) {
        return pawService.searchPage(query);
    }

    @GetMapping("/foster/details")
    public FosterDetails fetchFosterDetails(@RequestParam String id) {
        return pawService.getFosterDetail(id);
    }

    @PostMapping("/foster/create")
    public FosterDetails createFosterDetails(@RequestBody FosterDetails dto) {
        return pawService.createFosterData(dto);
    }

    @PostMapping("/user/register")
    public PawUser userRegister(@RequestBody PawUser dto) {
        return pawService.registerUser(dto);
    }

    @PostMapping("/user/login")
    public LoginUserResponse userLogin(@RequestBody PawUser dto) {
        return pawService.loginUser(dto);
    }

    @GetMapping("/user/profile")
    public UserProfile userLogin(@RequestParam String profileId) {
        return pawService.fetchUserProfile(profileId);
    }

    @GetMapping("/config/client")
    public PawConfig appConfig(@RequestParam String bn, @RequestParam String os) {
        return pawService.getClientConfig(os, bn);
    }

    @PostMapping("/config/save")
    public PawConfig createAppConfig(@RequestParam String bn, @RequestParam String os, @RequestBody Object data) {
        return pawService.saveConfig(os, bn, data);
    }

    @PostMapping("/post")
    public PawPostResponse createFosterPost(@RequestBody PawPost data) {
        return pawService.createPost(data);
    }

    @GetMapping("/post")
    public PawPostResponse createFosterPost(@RequestParam String id) {
        return pawService.fetchPost(id);
    }

    @GetMapping("/post/all")
    public List<PawPostResponse> createFosterPost(@RequestParam String pageId, @RequestParam String size) {
        return pawService.fetchPaginatedPost(pageId, size);
    }

}

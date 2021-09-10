package com.moxo.app.controller;

import com.moxo.app.dto.paw.FosterDetails;
import com.moxo.app.entity.PawPageEntity;
import com.moxo.app.service.PawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paw")
public class PawController {

    @Autowired
    private PawService pawService;

    @GetMapping
    public PawPageEntity fetchLayout(@RequestParam Integer page, @RequestParam Integer size, @RequestParam String pageId) {
        return pawService.getLayout(pageId, page, size);
    }

    @GetMapping("/details")
    public FosterDetails fetchFosterDetails(@RequestParam String id) {
        return pawService.getFosterDetail(id);
    }

    @PostMapping("/create/foster")
    public FosterDetails fetchFosterDetails(@RequestBody FosterDetails entity) {
        return pawService.createFosterData(entity);
    }
}

package com.works.restcontrollers;

import com.works.useProfile.IConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProfileRestController {

    final IConfig iConfig;

    @GetMapping("/config")
    public Map config() {
        return iConfig.config();
    }

}

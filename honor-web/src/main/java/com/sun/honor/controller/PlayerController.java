package com.sun.honor.controller;

import com.sun.honor.service.RedisService;
import com.sun.honor.util.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sunjian.
 */
@RestController
@RequestMapping("/competition")
public class PlayerController {

    @Autowired
    private RedisService redisService;

    @RequestMapping("/enterForMatch")
    public String signUpForCompetition(String name) {
        redisService.addTempPlayer(name);
        return "报名成功,"+name+"你的选手编号是:" + NumberUtils.uuid();
    }


}

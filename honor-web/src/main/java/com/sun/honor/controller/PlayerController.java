package com.sun.honor.controller;

import com.sun.honor.service.PlayerService;
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

    private final RedisService redisService;
    private final PlayerService playerService;

    @Autowired
    public PlayerController(RedisService redisService, PlayerService playerService) {
        this.redisService = redisService;
        this.playerService = playerService;
    }

    @RequestMapping("/enterForMatch")
    public String signUpForCompetition(String name) {
        redisService.addTempPlayer(name);
        return "报名成功," + name + "你的选手编号是:" + NumberUtils.uuid();
    }

    @RequestMapping("/group/player")
    public void groupPlayer() {
        playerService.groupPlayer();
    }

}

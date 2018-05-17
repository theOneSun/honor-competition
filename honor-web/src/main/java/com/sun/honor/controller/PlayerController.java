package com.sun.honor.controller;

import com.sun.honor.context.SessionKey;
import com.sun.honor.service.ApplyService;
import com.sun.honor.service.PlayerService;
import com.sun.honor.service.RedisService;
import com.sun.honor.util.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;


/**
 * @author sunjian.
 */
@RestController
@RequestMapping("/competition")
public class PlayerController {

    Logger logger = LoggerFactory.getLogger(PlayerController.class);
    private final RedisService redisService;
    private final PlayerService playerService;
    @Autowired
    private ApplyService applyService;

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

    @RequestMapping("/apply")
    public boolean applyForMatch(@RequestParam("matchId") String matchId) {
        String openId = (String) RequestContextHolder.getRequestAttributes()
                                                     .getAttribute(SessionKey.OPENID, RequestAttributes.SCOPE_SESSION);
        logger.info("当前登录用户的openId是:   " + openId);
        return applyService.applyForMatch(matchId);
    }
}

package com.sun.honor.service.impl;

import com.sun.honor.business.ApplyPlayer;
import com.sun.honor.dao.mapper.ApplyPlayerMapper;
import com.sun.honor.service.ApplyService;
import com.sun.honor.util.NumberUtils;
import com.sun.honor.wechat.UserInfoResponse;
import com.sun.honor.wechat.context.WechatMpUserInfoSuccessEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Created by sj on 2018/4/24.
 */
@Service
public class ApplyServiceImpl implements ApplyService {
    private final ApplyPlayerMapper applyPlayerMapper;

    @Value("${match.id}")
    private String matchId;
    @Autowired
    public ApplyServiceImpl(ApplyPlayerMapper applyPlayerMapper) {this.applyPlayerMapper = applyPlayerMapper;}

    @Override
    public void enterFor(UserInfoResponse userInfoResponse) {
        //1.获取当前用户信息
        //2.查询当前报名人数
        //3.判断是否还能报名
        ApplyPlayer applyPlayer = ApplyPlayer.builder()
                .id(NumberUtils.uuid())
                .name(userInfoResponse.getNickName())
                .openId(userInfoResponse.getOpenId())
                .applyTime(LocalDateTime.now())
                .applyTimeMilli(System.currentTimeMillis())
                .matchId(matchId)
                .build();
        applyPlayerMapper.save(applyPlayer);
    }

    @Override
    public boolean applyForMatch(String matchId) {
        /*
         * 获取用户信息
         */
        return false;
    }

    //事件监听
    @EventListener
    public void handleUserInfoEvent(WechatMpUserInfoSuccessEvent successEvent){
        UserInfoResponse userInfoResponse = successEvent.getUserInfoResponse();
        enterFor(userInfoResponse);
        // TODO: 2018/4/27 用户登录操作
    }
}

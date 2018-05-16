package com.sun.honor.controller;

import com.sun.honor.business.WechatUser;
import com.sun.honor.wechat.UserInfoResponse;
import com.sun.honor.wechat.context.WechatMpUserInfoSuccessEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * @author sunjian.
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(LoginController.class);
    public static final String OPEN_ID = "openId";

    @RequestMapping("/hello")
    public String sayHello() {
        return "hello";
    }

    //事件监听
    @EventListener
    public void handleUserInfoEvent(WechatMpUserInfoSuccessEvent successEvent) {
        UserInfoResponse userInfoResponse = successEvent.getUserInfoResponse();
        WechatUser wechatUser = new WechatUser();
        wechatUser.setOpenId(userInfoResponse.getOpenId());
        wechatUser.setNickName(userInfoResponse.getNickName());
        wechatUser.setSex(userInfoResponse.getSex());
        wechatUser.setUnionId(userInfoResponse.getUnionId());
        wechatUser.setHeadImgUrl(userInfoResponse.getHeadImgUrl());
        //todo 没有用户新增,有的话判断token是否需要更新


        // 存储用户信息(相当于登录)
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(wechatUser,"credentials", AuthorityUtils
                .commaSeparatedStringToAuthorityList("user")));
        RequestContextHolder.currentRequestAttributes()
                            .setAttribute(OPEN_ID, userInfoResponse.getOpenId(), RequestAttributes.SCOPE_SESSION);

        logger.info("userInfo:" + userInfoResponse);
        // TODO: 2018/4/27 用户登录操作
        /*SecurityContext context = SecurityContextHolder.getContext();
        if (context != null) {
            Authentication authentication = context.getAuthentication();
            if (authentication != null && authentication instanceof WechatUser) {
                logger.info("authentication" + authentication);
            }
            else {
                logger.info("authentication不明类型的用户" + authentication);
            }
        }
        else {
            logger.info("用户未登录");
        }*/
    }
}

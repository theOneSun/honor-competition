package com.sun.honor.controller;

import com.sun.honor.business.WechatUser;
import com.sun.honor.context.SessionKey;
import com.sun.honor.service.WechatUserService;
import com.sun.honor.wechat.AccessTokenResponse;
import com.sun.honor.wechat.UserInfoResponse;
import com.sun.honor.wechat.context.WechatMpUserInfoSuccessEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
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

    private final WechatUserService wechatUserService;

    @Autowired
    public LoginController(WechatUserService wechatUserService) {this.wechatUserService = wechatUserService;}

    @RequestMapping("/hello")
    public String sayHello() {
        return "hello";
    }

    //事件监听
    @EventListener
    public void handleUserInfoEvent(WechatMpUserInfoSuccessEvent successEvent) {
        UserInfoResponse userInfoResponse = successEvent.getUserInfoResponse();
        AccessTokenResponse accessTokenResponse = successEvent.getAccessTokenResponse();
        WechatUser wechatUser = new WechatUser();
        wechatUser.setUnionId(userInfoResponse.getUnionId());
        wechatUser.setOpenId(userInfoResponse.getOpenId());
        wechatUser.setAccessToken(accessTokenResponse.getAccessToken());
        wechatUser.setExpiresIn(accessTokenResponse.getExpiresIn());
        wechatUser.setRefreshToken(accessTokenResponse.getRefreshToken());
        wechatUser.setNickName(userInfoResponse.getNickName());
        wechatUser.setSex(userInfoResponse.getSex());
        wechatUser.setProvince(userInfoResponse.getProvince());
        wechatUser.setCity(userInfoResponse.getCity());
        wechatUser.setCountry(userInfoResponse.getCountry());
        wechatUser.setHeadImgUrl(userInfoResponse.getHeadImgUrl());
        wechatUser.setPrivilege(userInfoResponse.getPrivilege());

        //todo 没有用户新增,有的话判断token是否需要更新
        wechatUserService.saveOrUpdate(wechatUser);

        // 存储用户信息(相当于登录)
        SecurityContextHolder.getContext()
                             .setAuthentication(new UsernamePasswordAuthenticationToken(wechatUser, "credentials", AuthorityUtils
                                     .commaSeparatedStringToAuthorityList("user")));
        RequestContextHolder.currentRequestAttributes()
                            .setAttribute(SessionKey.OPENID, userInfoResponse.getOpenId(), RequestAttributes.SCOPE_SESSION);
    }
}

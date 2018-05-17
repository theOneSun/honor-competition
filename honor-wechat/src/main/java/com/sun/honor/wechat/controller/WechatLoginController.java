package com.sun.honor.wechat.controller;

import com.sun.honor.wechat.AccessTokenResponse;
import com.sun.honor.wechat.UserInfoResponse;
import com.sun.honor.wechat.context.WechatMpClient;
import com.sun.honor.wechat.context.WechatMpUserInfoSuccessEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author sunjian.
 */
@RestController
public class WechatLoginController {

    private Logger logger = LoggerFactory.getLogger(WechatLoginController.class);
    private WechatMpClient wechatMpClient;
    private final ApplicationEventPublisher publisher;


    public WechatLoginController(WechatMpClient wechatMpClient, ApplicationEventPublisher publisher) {
        this.wechatMpClient = wechatMpClient;
        this.publisher = publisher;
    }

    /**
     * 微信登录
     *
     * @param response
     * @throws IOException
     */
    @RequestMapping("/wechat/login")
    public void wechatLogin(HttpServletResponse response) throws IOException {
        String url = wechatMpClient.requestCode();
        response.sendRedirect(url);
    }

    /**
     * 微信登录回调的接口
     *
     * @param code 微信回调带着code参数
     * @param response
     * @throws IOException
     */
    @RequestMapping("/wechat/token")
    public void printCode(@RequestParam("code") String code, HttpServletResponse response) throws IOException {

        AccessTokenResponse accessTokenResponse = wechatMpClient.requestAccessToken(code);

        //获取用户信息
        UserInfoResponse userInfoResponse = wechatMpClient.getUserInfo(accessTokenResponse);
        WechatMpUserInfoSuccessEvent successEvent = new WechatMpUserInfoSuccessEvent(this, userInfoResponse, accessTokenResponse);
        //发布事件
        publisher.publishEvent(successEvent);
        SavedRequest savedRequest = (SavedRequest) RequestContextHolder.getRequestAttributes()
                                                                       .getAttribute("SPRING_SECURITY_SAVED_REQUEST", RequestAttributes.SCOPE_SESSION);
        response.sendRedirect(savedRequest.getRedirectUrl());
    }


}

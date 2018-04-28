package com.sun.honor.wechat.controller;

import com.sun.honor.wechat.AccessTokenResponse;
import com.sun.honor.wechat.UserInfoResponse;
import com.sun.honor.wechat.context.WechatMpClient;
import com.sun.honor.wechat.context.WechatMpUserInfoSuccessEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author sunjian.
 */
@RestController
public class WechatLoginController {

    private WechatMpClient wechatMpClient;
    private final ApplicationEventPublisher publisher;

    public WechatLoginController(WechatMpClient wechatMpClient, ApplicationEventPublisher publisher) {
        this.wechatMpClient = wechatMpClient;
        this.publisher = publisher;
    }

    //用户发起的登录接口
    @RequestMapping("/wechat/login")
    public void wechatLogin(HttpServletResponse response) throws IOException {
        String url = wechatMpClient.requestCode();
        response.sendRedirect(url);
    }

    //微信回调
    @RequestMapping("/wechat/token")
    public void printCode(@RequestParam("code") String code) throws IOException {
//        System.out.println("返回的code是: " + code);
        AccessTokenResponse accessTokenResponse = wechatMpClient.requestAccessToken(code);
        //判断用户是否存在,不存在需要保存用户
//        String openId = accessTokenResponse.getOpenId();
        //获取用户信息
        UserInfoResponse userInfoResponse = wechatMpClient.getUserInfo(accessTokenResponse);
        WechatMpUserInfoSuccessEvent successEvent = new WechatMpUserInfoSuccessEvent(this,userInfoResponse);
        //发布事件
        publisher.publishEvent(successEvent);
    }


}

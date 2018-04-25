package com.sun.honor.wechat.controller;

import com.sun.honor.wechat.context.WechatMpClient;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author sunjian.
 */
@RestController
public class WechatLoginController {

    private WechatMpClient wechatMpClient;

    //用户发起的登录接口
    public void wechatLogin() throws IOException {
        wechatMpClient.requestCode();
    }

    //微信回调
    @RequestMapping("/wechat/code")
    public void printCode(@RequestParam("code") String code,
                          @RequestParam(value = "redirect",required = false) String redirect) {
        System.out.println("返回的code是: " + code);
        System.out.println("返回的redirect是: " + redirect);
    }


}

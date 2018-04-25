package com.sun.honor.wechat.controller;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author sunjian.
 */
@RestController
public class WechatLoginController {
//    private static final String AUTHORIZE_CODE_PATH = "/public/wechat/mp/authorize_code";
//    private final WechatMpProperties weChatMpProperties;
//    private final WeChatMpClient weChatMpClient;
//    private final ApplicationEventPublisher publisher;
//    public WechatLoginController( WeChatMpProperties weChatMpProperties,
//                               WeChatMpClient weChatMpClient,
//                               ApplicationEventPublisher publisher) {
//        this.weChatMpProperties = weChatMpProperties;
//        this.weChatMpClient = weChatMpClient;
//        this.publisher = publisher;
//    }
//
//    /**
//     * 重定向到微信授权地址。
//     *
//     * @param redirect 应用自身回调地址
//     */
//    @GetMapping(path = "${wechat.mp.access-authorize-path:/public/wechat/mp/access_authorize}")
//    public RedirectView accessAuthorize(@RequestParam("redirect") String redirect,
//                                        @RequestParam(value = "requestUrl", required = false) String targetUrl) throws UnsupportedEncodingException {
//        // final String path = StringUtils.hasText(this.weChatMpProperties.getAuthorizeCodePath())
//        //     ? this.weChatMpProperties.getAuthorizeCodePath()
//        //     : AUTHORIZE_CODE_PATH;
//        String redirectUri = WeChatUtils.joinPath(weChatMpProperties.getNotifyAddress(), redirect);
////        return new RedirectView(WeChatMpUtils.generateAuthorizeUrl(this.weChatMpProperties.getAppId(), redirectUri, AuthorizeScope.BASE));
//        if (null == targetUrl) {
//            targetUrl = "";
//        }
//        return new RedirectView(WeChatMpUtils.generateAuthorizeUrl(weChatMpProperties.getAppId(),
//                redirectUri,
//                AuthorizeScope.BASE,
//                URLEncoder.encode(targetUrl, "utf-8")));
//    }
//
//    /**
//     * 微信回调接口，提供给微信在授权之后的重定向
//     *
//     * @return 重定向到指定地址, {@link #accessAuthorize(String, String)}中指定的 redirect
//     */
//    @GetMapping(path = "${wechat.mp.authorize-code-path:" + AUTHORIZE_CODE_PATH + '}')
//    public RedirectView authorizeCode(@RequestParam("code") String code,
//                                      @RequestParam("redirect") String redirect) {
//        Call<WeChatMpAccessTokenResponse> responseCall = weChatMpClient.snsOauth2AccessToken(weChatMpProperties
//                        .getAppId(),
//                weChatMpProperties
//                        .getAppSecret(),
//                code,
//                "authorization_code");
//
//        Response<WeChatMpAccessTokenResponse> response = Try.of(responseCall::execute)
//                .getOrElseThrow(WeChatMpException::new);
//        WeChatMpUtils.checkResponse(response);
//        WeChatMpAccessTokenResponse body = response.body();
//        WeChatMpUtils.checkResponseBody(body);
//
//        WeChatMpAuthenticationSuccessEvent successEvent = new WeChatMpAuthenticationSuccessEvent(body);
//        successEvent.setRedirect(redirect);
//        publisher.publishEvent(successEvent);
//        return new RedirectView(redirect);
//    }

    //微信回调
    @RequestMapping("/wechat/code")
    public void printCode(@RequestParam("code") String code,
                          @RequestParam(value = "redirect",required = false) String redirect) {
        System.out.println("返回的code是: " + code);
        System.out.println("返回的redirect是: " + redirect);
    }


}

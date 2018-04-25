package com.sun.honor.wechat.context;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author sunjian.
 */
@Data
@Component
@PropertySource("application.properties")
@ConfigurationProperties(prefix = "wechat.mp")
public class WechatMpProperties {
    /**
     * 公众号的唯一标识
     */
    private String appId;

    private String appSecret;

    private String notifyAddress;

    /**
     * 授权后重定向的回调链接path
     */
    private String authorizeCodeCallBackPath;

    private String accessAuthorizePath;

    /**
     * 微信oauth授权登录请求code的url
     */
    private String requestCodeUrl;
    /**
     * 微信oauth授权获取token的url
     */
    private String requestAccessTokenUrl;
}

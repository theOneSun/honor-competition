package com.sun.honor.wechat.context;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author sunjian.
 */
@Data
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
    private String authorizeCodePath;

    private String accessAuthorizePath;
}

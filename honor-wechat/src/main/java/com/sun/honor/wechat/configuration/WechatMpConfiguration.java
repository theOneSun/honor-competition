package com.sun.honor.wechat.configuration;

import com.sun.honor.wechat.context.WechatMpClient;
import com.sun.honor.wechat.context.WechatMpProperties;
import com.sun.honor.wechat.controller.WechatLoginController;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sunjian.
 */
@Configuration
@EnableConfigurationProperties(WechatMpProperties.class)
public class WechatMpConfiguration {
    private final WechatMpProperties wechatMpProperties;

    public WechatMpConfiguration(WechatMpProperties wechatMpProperties) {
        this.wechatMpProperties = wechatMpProperties;
    }

    @Bean
    @ConditionalOnMissingBean
    public WechatLoginController weChatMpController(WechatMpClient wechatMpClient, ApplicationEventPublisher publisher) {
        return new WechatLoginController(wechatMpClient, publisher);
    }

    @Bean
    @ConditionalOnMissingBean
    public WechatMpClient weChatMpClient() {
        return new WechatMpClient(wechatMpProperties);
    }

}

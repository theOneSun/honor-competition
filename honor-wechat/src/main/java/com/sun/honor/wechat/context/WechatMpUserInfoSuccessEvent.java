package com.sun.honor.wechat.context;

import com.sun.honor.wechat.UserInfoResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.ApplicationEvent;

/**
 * @author sunjian.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WechatMpUserInfoSuccessEvent extends ApplicationEvent {
    private UserInfoResponse userInfoResponse;

    public WechatMpUserInfoSuccessEvent(Object source, UserInfoResponse userInfoResponse) {
        super(source);
        this.userInfoResponse = userInfoResponse;
    }
}

package com.sun.honor.service;

import com.sun.honor.wechat.UserInfoResponse;

/**
 * @author sunjian.
 */
public interface WechatUserService {
    void handleUserInfo(UserInfoResponse userInfoResponse);
}

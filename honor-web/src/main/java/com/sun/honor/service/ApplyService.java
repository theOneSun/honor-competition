package com.sun.honor.service;

import com.sun.honor.wechat.UserInfoResponse;

/**
 * 申请报名
 * Created by sj on 2018/4/24.
 */
public interface ApplyService {
    void enterFor(UserInfoResponse userInfoResponse);
}

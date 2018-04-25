package com.sun.honor.wechat.context;

/**
 * 微信用户授权scope参数枚举
 * Created by sj on 2018/4/25.
 */
public enum WechatAuthorityScope {
    BASE("base"),
    USER_INFO("userinfo"),;
    private String scope;

    WechatAuthorityScope(String scope) {
        this.scope = scope;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}

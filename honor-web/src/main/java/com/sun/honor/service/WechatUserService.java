package com.sun.honor.service;

import com.sun.honor.business.WechatUser;

/**
 * @author sunjian.
 */
public interface WechatUserService {

    /**
     * 新增或更新微信用户
     *
     * @param wechatUser 微信用户
     */
    void saveOrUpdate(WechatUser wechatUser);
}

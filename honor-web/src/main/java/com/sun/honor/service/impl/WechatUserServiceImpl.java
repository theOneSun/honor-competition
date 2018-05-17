package com.sun.honor.service.impl;

import com.sun.honor.business.WechatUser;
import com.sun.honor.dao.mapper.WechatUserMapper;
import com.sun.honor.service.WechatUserService;
import com.sun.honor.util.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * @author sunjian.
 */
@Service
public class WechatUserServiceImpl implements WechatUserService {
    private Logger logger = LoggerFactory.getLogger(WechatUserServiceImpl.class);
    private final WechatUserMapper wechatUserMapper;

    @Autowired
    public WechatUserServiceImpl(WechatUserMapper wechatUserMapper) {this.wechatUserMapper = wechatUserMapper;}

    @Override
    public void saveOrUpdate(WechatUser wechatUser) {
        WechatUser userByOpenId = wechatUserMapper.getByOpenId(wechatUser.getOpenId());
        if (ObjectUtils.isEmpty(userByOpenId)) {
            wechatUser.setId(NumberUtils.uuid());
            wechatUserMapper.save(wechatUser);
            logger.info("新增微信用户:" + wechatUser);
        }
        else {
            wechatUserMapper.update(wechatUser);
            wechatUser.setId(userByOpenId.getId());
            wechatUser.setCreateTime(userByOpenId.getCreateTime());
            logger.info("更新微信用户:" + wechatUser);
        }
    }
}

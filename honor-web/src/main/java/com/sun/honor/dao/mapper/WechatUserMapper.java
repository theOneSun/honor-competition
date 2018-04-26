package com.sun.honor.dao.mapper;

import com.sun.honor.business.WechatUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author sunjian.
 */
@Mapper
public interface WechatUserMapper {

    void save(WechatUser wechatUser);
}

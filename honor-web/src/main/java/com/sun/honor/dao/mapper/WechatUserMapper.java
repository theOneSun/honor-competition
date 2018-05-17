package com.sun.honor.dao.mapper;

import com.sun.honor.business.WechatUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author sunjian.
 */
@Mapper
public interface WechatUserMapper {

    /**
     * 保存
     *
     * @param wechatUser
     */
    void save(WechatUser wechatUser);

    /**
     * 根据openId查询微信用户
     *
     * @param openId 当前用户的openId
     * @return 微信用户对象
     */
    @Select("select * from user_wechat where open_id = #{openId}")
    WechatUser getByOpenId(@Param("openId") String openId);

    /**
     * 更新
     *
     * @param wechatUser 微信用户对象
     */
    void update(WechatUser wechatUser);
}

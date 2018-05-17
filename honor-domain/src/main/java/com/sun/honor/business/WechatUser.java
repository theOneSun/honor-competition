package com.sun.honor.business;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author sunjian.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WechatUser {
    private String id;
    private String unionId;
    private String openId;
    private String accessToken;
    private LocalDateTime lastLoginTime;
    private Integer expiresIn;
    private String refreshToken;
    private LocalDateTime createTime;
    private String nickName;
    private String sex;
    private String province;
    private String city;
    /**
     * 国家，如中国为CN
     */
    private String country;
    /**
     * 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空
     */
    private String headImgUrl;
    /**
     * 用户特权信息，json数组，如微信沃卡用户为（chinaunicom）
     */
    private String privilege;
}

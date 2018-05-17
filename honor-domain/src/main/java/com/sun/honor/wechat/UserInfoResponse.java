package com.sun.honor.wechat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author sunjian.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserInfoResponse extends BaseWechatMpResponse {
    @JsonProperty("openid")
    private String openId;

    @JsonProperty("nickname")
    private String nickName;

    @JsonProperty("sex")
    private String sex;

    @JsonProperty("language")
    private String language;

    @JsonProperty("city")
    private String city;

    @JsonProperty("province")
    private String province;

    @JsonProperty("country")
    private String country;

    @JsonProperty("headimgurl")
    private String headImgUrl;

    @JsonProperty("privilege")
    private String privilege;

    @JsonProperty("unionid")
    private String unionId;

}

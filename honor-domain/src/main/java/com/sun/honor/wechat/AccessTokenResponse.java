package com.sun.honor.wechat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 请求accessToken返回的结果
 * @author sunjian.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AccessTokenResponse extends BaseWechatMpResponse{

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expires_in")
    private Integer expiresIn;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("openid")
    private String openId;

    @JsonProperty("scope")
    private String scope;
}

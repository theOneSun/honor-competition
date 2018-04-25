package com.sun.honor.wechat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author sunjian.
 */
@SuppressWarnings("SpellCheckingInspection")
@Data
public class BaseWechatMpResponse {
    @JsonProperty("errcode")
    protected String errorCode;
    @JsonProperty("errmsg")
    protected String errorMessage;
}

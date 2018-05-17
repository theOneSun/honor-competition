package com.sun.honor.wechat.context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.sun.honor.wechat.AccessTokenResponse;
import com.sun.honor.wechat.UserInfoResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * 封装所有对微信的请求
 *
 * @author sunjian.
 */
public class WechatMpClient {

    Logger logger = LoggerFactory.getLogger(WechatMpClient.class);
    private WechatMpProperties wechatMpProperties;
    private OkHttpClient okHttpClient = new OkHttpClient();

    public WechatMpClient(WechatMpProperties wechatMpProperties) {
        this.wechatMpProperties = wechatMpProperties;
    }

    /**
     * 获取code
     *
     * @return 请求code的url
     * @throws IOException
     */
    public String requestCode() throws IOException {
        return wechatMpProperties.getRequestCodeUrl()
                + "?appid=" + wechatMpProperties.getAppId()
                + "&redirect_uri=" + URLEncoder.encode(wechatMpProperties.getAuthorizeCodeCallBackPath(), "utf-8")
                + "&response_type=code&scope=" + WechatAuthorityScope.USER_INFO.getScope()
                + "&state=no_use#wechat_redirect";
    }

    /**
     * 获取accessToken
     *
     * @param code
     * @return
     * @throws IOException
     */
    public AccessTokenResponse requestAccessToken(String code) throws IOException {
        String url = wechatMpProperties.getRequestAccessTokenUrl()
                + "?appid=" + wechatMpProperties.getAppId()
                + "&secret=" + wechatMpProperties.getAppSecret()
                + "&code=" + code
                + "&grant_type=authorization_code";

        Request request = new Request.Builder().url(url).build();
        Response response = okHttpClient.newCall(request).execute();
        if (response.isSuccessful()) {
            ResponseBody body = response.body();
            return JSON.parseObject(body.string(), new TypeReference<AccessTokenResponse>() {});
        }
        else {
            logger.error("获取accessToken错误", response);
            return null;
        }
    }

    /**
     * 刷新accessToken
     *
     * @param refreshToken
     * @return
     * @throws IOException
     */
    public AccessTokenResponse refreshAccessToken(String refreshToken) throws IOException {
        String url = wechatMpProperties.getRefreshTokenUrl()
                + "?appid=" + wechatMpProperties.getAppId()
                + "&grant_type=refresh_token&refresh_token=" + refreshToken;
        Request request = new Request.Builder().url(url).build();
        Response response = okHttpClient.newCall(request).execute();
        if (response.isSuccessful()) {
            ResponseBody body = response.body();
            return JSON.parseObject(body.string(), new TypeReference<AccessTokenResponse>() {});
        }
        else {
            return null;
        }
    }

    /**
     * 获取用户信息
     *
     * @param accessTokenResponse
     * @return
     * @throws IOException
     */
    public UserInfoResponse getUserInfo(AccessTokenResponse accessTokenResponse) throws IOException {
        String url = wechatMpProperties.getRequestUserInfoUrl()
                + "?access_token=" + accessTokenResponse.getAccessToken()
                + "&openid=" + accessTokenResponse.getOpenId()
                + "&lang=zh_CN";
        Request request = new Request.Builder().url(url).build();
        Response response = okHttpClient.newCall(request).execute();
        if (response.isSuccessful()) {
            ResponseBody body = response.body();
            return JSON.parseObject(body.string(), new TypeReference<UserInfoResponse>() {});
        }
        else {
            return null;
        }
    }
}

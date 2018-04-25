package com.sun.honor.wechat.context;

import com.sun.honor.wechat.AccessTokenResponse;
import com.sun.honor.wechat.UserInfoResponse;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * 封装所有对微信的请求
 *
 * @author sunjian.
 */
public class WechatMpClient {

    private WechatMpProperties wechatMpProperties;
    private WechatAuthorityScope wechatAuthorityScope;
    private OkHttpClient okHttpClient = new OkHttpClient();

    //todo 获取code
    public void requestCode() throws IOException {
        String requestCodeBaseUrl = "https://open.weixin.qq.com/connect/oauth2/authorize";
        String url = requestCodeBaseUrl
                + "?appid=" + wechatMpProperties.getAppId()
                + "&redirect_uri=" + URLEncoder.encode(wechatMpProperties.getAuthorizeCodeCallBackPath(),"utf-8")
                + "&response_type=code&scope=" + WechatAuthorityScope.USER_INFO.getScope()
                + "&state=no_use#wechat_redirect";
        Request request = new Request.Builder().url(url).build();
        Response response = okHttpClient.newCall(request).execute();
        System.out.println(response.code());
    }

    //todo 获取accessToken
    public AccessTokenResponse requestAccessToken() {




        return null;
    }

    //todo 刷新accessToken
    public AccessTokenResponse refreshAccessToken() {
        return null;
    }

    //todo
    public UserInfoResponse getUserInfo() {
        return null;
    }
}

package com.sun.honor.wechat.context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.sun.honor.wechat.AccessTokenResponse;
import com.sun.honor.wechat.UserInfoResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
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

    private WechatMpProperties wechatMpProperties;
    private OkHttpClient okHttpClient = new OkHttpClient();

    public WechatMpClient(WechatMpProperties wechatMpProperties){
        this.wechatMpProperties = wechatMpProperties;
    }

    //todo 获取code
    public String requestCode() throws IOException {
        String url = wechatMpProperties.getRequestCodeUrl()
                + "?appid=" + wechatMpProperties.getAppId()
                + "&redirect_uri=" + URLEncoder.encode(wechatMpProperties.getAuthorizeCodeCallBackPath(), "utf-8")
                + "&response_type=code&scope=" + WechatAuthorityScope.USER_INFO.getScope()
                + "&state=no_use#wechat_redirect";
//        Request request = new Request.Builder().url(url).build();
//        Response response = okHttpClient.newCall(request).execute();
        return url;
//        System.out.println(response.code());
    }

    //todo 获取accessToken
    public AccessTokenResponse requestAccessToken(String code) throws IOException {
        String url = wechatMpProperties.getRequestAccessTokenUrl()
                + "?appid=" + wechatMpProperties.getAppId()
                + "&secret=" + wechatMpProperties.getAppSecret()
                + "&code=" + code
                + "&grant_type=authorization_code";

        Request request = new Request.Builder().url(url).build();
        Response response = okHttpClient.newCall(request).execute();
        System.out.println(response.code());
        if (response.isSuccessful()){
            ResponseBody body = response.body();
            return JSON.parseObject(body.string(), new TypeReference<AccessTokenResponse>() {});
        }else {
            System.out.println(response);
            return null;
        }
    }

    //todo 刷新accessToken
    public AccessTokenResponse refreshAccessToken() {
        return null;
    }

    //todo
    public UserInfoResponse getUserInfo(AccessTokenResponse accessTokenResponse) throws IOException {
        String url = wechatMpProperties.getRequestUserInfoUrl()
                + "?access_token=" + accessTokenResponse.getAccessToken()
                + "&openid=" + accessTokenResponse.getOpenId()
                + "&lang=zh_CN";
        Request request = new Request.Builder().url(url).build();
        Response response = okHttpClient.newCall(request).execute();
        if (response.isSuccessful()){
            ResponseBody body = response.body();
            return JSON.parseObject(body.string(), new TypeReference<UserInfoResponse>() {});
        }else {
            return null;
        }
    }
}

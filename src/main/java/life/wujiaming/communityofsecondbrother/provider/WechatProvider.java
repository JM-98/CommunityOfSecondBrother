package life.wujiaming.communityofsecondbrother.provider;

import com.alibaba.fastjson.JSON;
import life.wujiaming.communityofsecondbrother.dto.AccessTokenDTO;
import life.wujiaming.communityofsecondbrother.dto.GithubUser;
import life.wujiaming.communityofsecondbrother.dto.WechatUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Wu JiaMing, head of National Aeronautics and Space Administration
 * @date 02/08/2023 14:43
 */

@Component
public class WechatProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType
                = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        // json要转换成String.JSON
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://api.weixin.qq.com/sns/oauth2/access_token")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            // Ctrl + Alt + N —— Inline Variable 将变量放到原文中
            String token = string.split("&")[0].split("=")[1];
            return token;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public WechatUser getWechatUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            // 把类型为String的JSON对象，转换为GitHubUser的类对象
            WechatUser wechatUser = JSON.parseObject(string, WechatUser.class);
            return wechatUser;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

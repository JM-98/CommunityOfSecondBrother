package life.wujiaming.communityofsecondbrother.provider;

import com.alibaba.fastjson.JSON;
import life.wujiaming.communityofsecondbrother.dto.AccessTokenDTO;
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
}

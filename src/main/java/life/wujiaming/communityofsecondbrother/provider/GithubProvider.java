package life.wujiaming.communityofsecondbrother.provider;

import com.alibaba.fastjson.JSON;
import life.wujiaming.communityofsecondbrother.dto.AccessTokenDTO;
import life.wujiaming.communityofsecondbrother.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author Wu JiaMing, head of National Aeronautics and Space Administration
 * @date 20/07/2023 17:02
 * provider package : 提供对第三方支持的能力
 * component : 把此注解标注的类注册为Spring的上下文；在调用时，不需要实例化该对象
 */

@Component
public class GithubProvider {
//    //读取超时为60s
//    private static final long READ_TIMEOUT = 60000;
//    //写入超时为60s
//    private static final long WRITE_TIMEOUT = 60000;
//    //连接超时为60s
//    private static final long CONNECT_TIMEOUT = 60000;

    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType
                = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
//        client.newBuilder().readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
//                .writeTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
//                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);

        // json要转换成String.JSON
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
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

    public GithubUser getGithubUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            // 把类型为String的JSON对象，转换为GitHubUser的类对象
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

package life.wujiaming.communityofsecondbrother.controller;

import jakarta.servlet.http.HttpServletRequest;
import life.wujiaming.communityofsecondbrother.dto.AccessTokenDTO;
import life.wujiaming.communityofsecondbrother.dto.GithubUser;
import life.wujiaming.communityofsecondbrother.mapper.UserMapper;
import life.wujiaming.communityofsecondbrother.model.User;
import life.wujiaming.communityofsecondbrother.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

/**
 * @author Wu JiaMing, head of National Aeronautics and Space Administration
 * @date 20/07/2023 16:55
 * Spring 通过@Autowired注解，提前实例化对象并放到容器里
 */

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserMapper userMapper;

    // @Value注解会去配置文件里读取github.client.id的值，并赋值给clientId
    @Value("${github.client.id}")
    private String githubClientId;

    @Value("${github.client.secret}")
    private String githubClientSecret;

    @Value("${github.redirect.uri}")
    private String githubRedirectUri;

    private String appId;

    private String appSecret;

    private String redirectUri;

//    @GetMapping("/callback")
    public String githubCallback(@RequestParam(name = "code") String code, @RequestParam(name = "state") String state, HttpServletRequest request) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClientId(githubClientId);
        accessTokenDTO.setClientSecret(githubClientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirectUri(githubRedirectUri);
        accessTokenDTO.setState(state);
        // 获取GitHub用户的信息
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getGithubUser(accessToken);
        System.out.println(githubUser.getName());
        // 登录成功
        if (githubUser != null) {
            User user = new User();
            System.out.println(githubUser.getName());
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setToken(UUID.randomUUID().toString());
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(System.currentTimeMillis());
            userMapper.insert(user);
            // 将User对象写进request中的session
            request.getSession().setAttribute("githubUser", githubUser);
        } else {
            // 登录失败，重新登录

        }
        // 登录成功后，系统返回index页面；加上redirect后，网址会修改为index
        return "redirect:/";
    }

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code, @RequestParam(name = "state") String state, HttpServletRequest httpServletRequest) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClientId(appId);
        accessTokenDTO.setClientSecret(appSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirectUri(redirectUri);
        accessTokenDTO.setState(state);
        // 获取Wechat用户的信息


        // 登录成功后，系统返回index页面；加上redirect后，网址会修改为index
        return "redirect:/";
    }

}

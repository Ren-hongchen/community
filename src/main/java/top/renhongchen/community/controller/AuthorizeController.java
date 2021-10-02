package top.renhongchen.community.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.renhongchen.community.dto.AccessTokenDTO;
import top.renhongchen.community.dto.GithubUserDTO;
import top.renhongchen.community.model.User;
import top.renhongchen.community.provider.GithubProvider;
import top.renhongchen.community.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserService userService;

    @Value("${Github.clientId}")
    private String clientId;

    @Value("${Github.clientSecret}")
    private String clientSecret;

    @Value("${Github.redirectUri}")
    private String redirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           HttpServletResponse httpServletResponse) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        //System.out.printf(accessToken);
        GithubUserDTO githubUserDTO = githubProvider.getUser(accessToken);
        //System.out.printf(githubUserDTO.getName());
        if (githubUserDTO != null && githubUserDTO.getId() != null) { //登录成功
            User user = new User();
            user.setName(githubUserDTO.getName());
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setAccountId(String.valueOf(githubUserDTO.getId()));
            user.setAvatarUrl(githubUserDTO.getAvatarUrl());
            userService.createOrUpdate(user);
            httpServletResponse.addCookie(new Cookie("token",token));
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response) {
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}

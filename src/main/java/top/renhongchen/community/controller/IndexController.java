package top.renhongchen.community.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import top.renhongchen.community.mapper.UserMapper;
import top.renhongchen.community.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest httpServletRequest) {
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies == null) {
            return "index";
        }
        for (Cookie cookie:cookies) {
            if(cookie.getName().equals("token")) {
                String token = cookie.getValue();
                User user = userMapper.findByToken(token);
                if(user != null) {
                    httpServletRequest.getSession().setAttribute("user",user);
                }
                break;
            }
        }

        return "index";
    }
}

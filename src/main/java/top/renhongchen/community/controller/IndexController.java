package top.renhongchen.community.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import top.renhongchen.community.dto.PostDTO;
import top.renhongchen.community.mapper.UserMapper;
import top.renhongchen.community.model.User;
import top.renhongchen.community.service.PostService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PostService postService;

    @GetMapping("/")
    public String index(HttpServletRequest httpServletRequest,
                        Model model) {
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

        List<PostDTO> postList = postService.list();
        model.addAttribute("postList",postList);

        return "index";
    }
}

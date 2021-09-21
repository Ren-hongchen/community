package top.renhongchen.community.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.renhongchen.community.dto.PaginationDTO;
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
                        Model model,
                        @RequestParam(value = "page",defaultValue = "1") Integer page,
                        @RequestParam(value = "size",defaultValue = "5") Integer size) {
        Cookie[] cookies = httpServletRequest.getCookies();

        PaginationDTO pagination = postService.list(page, size);
        model.addAttribute("pagination", pagination);

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

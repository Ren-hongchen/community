package top.renhongchen.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.renhongchen.community.mapper.PostMapper;
import top.renhongchen.community.mapper.UserMapper;
import top.renhongchen.community.model.Post;
import top.renhongchen.community.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PostMapper postMapper;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @RequestMapping("/publish")
    public String DoPublish(@RequestParam(value = "title",required = false) String title,
                            @RequestParam(value = "description",required = false) String description,
                            @RequestParam(value = "tag",required = false) String tag,
                            HttpServletRequest request,
                            Model model) {

        if(title == null || title == "") {
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if (description == null || description == "") {
            model.addAttribute("error","描述不能为空");
            return "publish";
        }
        if (tag == null || tag == "") {
            model.addAttribute("error","标签不能为空");
            return "publish";
        }

        User user = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                String token = cookie.getValue();
                user = userMapper.findByToken(token);
                if (user != null) {
                    request.getSession().setAttribute("user", user);
                }
                break;
            }
        }
        if (user == null) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }

        Post post = new Post();
        post.setTitle(title);
        post.setDescription(description);
        post.setTag(tag);
        post.setCreator(user.getId());
        post.setGmt_create(System.currentTimeMillis());
        post.setGmt_modified(post.getGmt_create());
        postMapper.insert(post);

        return "redirect:/";
    }
}

package top.renhongchen.community.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import top.renhongchen.community.dto.PaginationDTO;
import top.renhongchen.community.model.User;
import top.renhongchen.community.service.PostService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Autowired
    private PostService postService;

    @GetMapping("/profile/{action}")
    public String profile(HttpServletRequest request,
                          @PathVariable(value = "action") String action,
                          @RequestParam(value = "page",defaultValue = "1") Integer page,
                          @RequestParam(value = "size",defaultValue = "5") Integer size,
                          Model model) {

        User user = (User) request.getSession().getAttribute("user");
        if(user == null) {
            return "redirect:/";
        }

        if("posts".equals(action)) {
            model.addAttribute("section","posts");
            model.addAttribute("sectionName","我的问题");
        } else if("replies".equals(action)) {
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }

        PaginationDTO paginationDTO = postService.list(user.getId(),page,size);

        model.addAttribute("pagination", paginationDTO);

        return "profile";
    }
}

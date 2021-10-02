package top.renhongchen.community.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.renhongchen.community.dto.PostDTO;
import top.renhongchen.community.mapper.PostMapper;
import top.renhongchen.community.service.PostService;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/post/{id}")
    public String post(@PathVariable(value = "id") Integer id,
                       Model model) {
        PostDTO postDTO = postService.getById(id);
        postService.addView(id);
        model.addAttribute("post",postDTO);

        return "post";
    }
}

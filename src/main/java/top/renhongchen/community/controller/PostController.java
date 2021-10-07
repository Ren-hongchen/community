package top.renhongchen.community.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.renhongchen.community.dto.CommentDTO;
import top.renhongchen.community.dto.PostDTO;
import top.renhongchen.community.service.CommentService;
import top.renhongchen.community.service.PostService;

import java.util.List;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/post/{id}")
    public String post(@PathVariable(value = "id") Long id,
                       Model model) {
        PostDTO postDTO = postService.getById(id);

        List<CommentDTO> commentDTOList = commentService.listByPostId(id);

        postService.addViewCount(id);
        model.addAttribute("post",postDTO);
        model.addAttribute("comments",commentDTOList);

        return "post";
    }
}

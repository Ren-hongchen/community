package top.renhongchen.community.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.renhongchen.community.dto.PaginationDTO;
import top.renhongchen.community.service.PostService;


@Controller
public class IndexController {

    @Autowired
    private PostService postService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(value = "page",defaultValue = "1") Integer page,
                        @RequestParam(value = "size",defaultValue = "5") Integer size) {

        PaginationDTO pagination = postService.list(page, size);
        model.addAttribute("pagination", pagination);

        return "index";
    }
}

package top.renhongchen.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import top.renhongchen.community.dto.CommentCreateDTO;
import top.renhongchen.community.dto.CommentDTO;
import top.renhongchen.community.dto.ResultDTO;
import top.renhongchen.community.exception.CustomizeAppException;
import top.renhongchen.community.model.Comment;
import top.renhongchen.community.model.User;
import top.renhongchen.community.service.CommentService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object comment(@RequestBody CommentCreateDTO commentCreateDTO,
                          HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if(user == null) {
            throw new CustomizeAppException("001");
        }
        Comment comment = new Comment();
        comment.setContent(commentCreateDTO.getContent());
        comment.setCommentator(user.getId());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setLikeCount(0L);
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setType(commentCreateDTO.getType());
        comment.setGmtModified(0L);
        commentService.insert(comment);
        return ResultDTO.okOf();
    }

    @ResponseBody
    @RequestMapping(value = "/comment/{id}",method = RequestMethod.GET)
    public ResultDTO<List<CommentDTO>> sub_comment(@PathVariable(value = "id") Long id,
                                                   Model model) {
        List<CommentDTO> commentDTOs = commentService.listByTargetId(id, 2);
        return ResultDTO.okOf(commentDTOs);
    }
}

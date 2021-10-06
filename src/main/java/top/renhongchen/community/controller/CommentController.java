package top.renhongchen.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.renhongchen.community.dto.CommentDTO;
import top.renhongchen.community.dto.ResultDTO;
import top.renhongchen.community.exception.CustomizeAppException;
import top.renhongchen.community.exception.CustomizeErrorCode;
import top.renhongchen.community.mapper.CommentMapper;
import top.renhongchen.community.model.Comment;
import top.renhongchen.community.model.User;
import top.renhongchen.community.service.CommentService;

import javax.servlet.http.HttpServletRequest;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object comment(@RequestBody CommentDTO commentDTO,
                          HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if(user == null) {
            throw new CustomizeAppException("001");
        }
        Comment comment = new Comment();
        comment.setComment(commentDTO.getContent());
        comment.setCommentator(user.getId());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setLikeCount(0L);
        comment.setParentId(commentDTO.getParentId());
        comment.setType(commentDTO.getType());
        comment.setGmtModified(0L);
        commentService.insert(comment);
        return ResultDTO.okOf();
    }
}

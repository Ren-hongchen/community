package top.renhongchen.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.renhongchen.community.dto.CommentDTO;
import top.renhongchen.community.mapper.CommentMapper;
import top.renhongchen.community.model.Comment;

@RestController
public class CommentController {

    @Autowired
    private CommentMapper commentMapper;

    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object comment(@RequestBody CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setComment(commentDTO.getContent());
        comment.setCommentator(1);
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setLikeCount(0L);
        comment.setParentId(commentDTO.getParentId());
        comment.setType(commentDTO.getType());
        comment.setGmtModified(0L);
        commentMapper.insertSelective(comment);
        return null;
    }
}

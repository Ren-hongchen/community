package top.renhongchen.community.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.renhongchen.community.dto.PostDTO;
import top.renhongchen.community.mapper.CommentMapper;
import top.renhongchen.community.mapper.PostMapper;
import top.renhongchen.community.model.Comment;
import top.renhongchen.community.model.Post;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private PostService postService;

    @Transactional
    public void insert(Comment comment) {
        //异常处理省略
        Long id = comment.getParentId();
        postService.addCommentCount(id);
        commentMapper.insertSelective(comment);
    }

}

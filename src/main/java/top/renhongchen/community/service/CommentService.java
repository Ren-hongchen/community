package top.renhongchen.community.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.renhongchen.community.mapper.CommentMapper;
import top.renhongchen.community.model.Comment;

@Transactional
@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private PostService postService;

    public void insert(Comment comment) {
        //异常处理省略
        Long id = comment.getParentId();
        postService.addCommentCount(id);
        commentMapper.insertSelective(comment);
    }

}

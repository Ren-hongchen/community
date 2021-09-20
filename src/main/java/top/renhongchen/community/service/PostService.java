package top.renhongchen.community.service;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.renhongchen.community.dto.PostDTO;
import top.renhongchen.community.mapper.PostMapper;
import top.renhongchen.community.mapper.UserMapper;
import top.renhongchen.community.model.Post;
import top.renhongchen.community.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostMapper postMapper;

    @Autowired
    private UserMapper userMapper;

    public List<PostDTO> list() {
        List<Post> postList = postMapper.list();
        List<PostDTO> postDTOList = new ArrayList<>();
        for(Post post :postList) {
            User user = userMapper.findById(post.getCreator());
            PostDTO postDTO = new PostDTO();
            BeanUtils.copyProperties(post,postDTO);
            postDTO.setUser(user);
            postDTOList.add(postDTO);
        }
        return postDTOList;
    }
}

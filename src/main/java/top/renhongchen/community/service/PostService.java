package top.renhongchen.community.service;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.renhongchen.community.dto.PaginationDTO;
import top.renhongchen.community.dto.PostDTO;
import top.renhongchen.community.exception.CustomizeErrorCode;
import top.renhongchen.community.exception.CustomizeHardException;
import top.renhongchen.community.mapper.PostMapper;
import top.renhongchen.community.mapper.UserMapper;
import top.renhongchen.community.model.Post;
import top.renhongchen.community.model.PostExample;
import top.renhongchen.community.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostMapper postMapper;

    @Autowired
    private UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;

        Integer totalCount = (int) postMapper.countByExample(new PostExample());

        if (totalCount % size == 0) {
            totalPage = totalCount / size;
            if(totalPage == 0) {
                totalPage = 1;
            }
        } else {
            totalPage = totalCount / size + 1;
        }

        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }

        paginationDTO.setPagination(totalPage, page);

        //size*(page-1)
        Integer offset = size * (page - 1);
        List<Post> postList = postMapper.selectByExampleWithBLOBsWithRowbounds(new PostExample(),new RowBounds(offset,size));
        List<PostDTO> postDTOList = new ArrayList<>();
        for(Post post :postList) {
            User user = userMapper.selectByPrimaryKey(post.getCreator());
            PostDTO postDTO = new PostDTO();
            BeanUtils.copyProperties(post,postDTO);
            postDTO.setUser(user);
            postDTOList.add(postDTO);
        }
        paginationDTO.setPostList(postDTOList);
        return paginationDTO;
    }

    public PaginationDTO list(Long id, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;

        PostExample postExample = new PostExample();
        postExample.createCriteria().andCreatorEqualTo(id);
        Integer totalCount = (int) postMapper.countByExample(postExample);

        if (totalCount % size == 0) {
            totalPage = totalCount / size;
            if(totalPage == 0) {
                totalPage=1;
            }
        } else {
            totalPage = totalCount / size + 1;
        }

        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }

        paginationDTO.setPagination(totalPage, page);

        //size*(page-1)
        Integer offset = size * (page - 1);
        PostExample postExample1 = new PostExample();
        postExample1.createCriteria().andCreatorEqualTo(id);
        List<Post> postList = postMapper.selectByExampleWithBLOBsWithRowbounds(postExample1,new RowBounds(offset,size));
        List<PostDTO> postDTOList = new ArrayList<>();
        for(Post post :postList) {
            User user = userMapper.selectByPrimaryKey(post.getCreator());
            PostDTO postDTO = new PostDTO();
            BeanUtils.copyProperties(post,postDTO);
            postDTO.setUser(user);
            postDTOList.add(postDTO);
        }
        paginationDTO.setPostList(postDTOList);
        return paginationDTO;
    }

    public PostDTO getById(Long id) {
        Post post = postMapper.selectByPrimaryKey(id);
        if (post == null) {
            throw new CustomizeHardException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        User user = userMapper.selectByPrimaryKey(post.getCreator());
        PostDTO postDTO = new PostDTO();
        BeanUtils.copyProperties(post,postDTO);
        postDTO.setUser(user);

        return postDTO;
    }

    public void createOrUpdate(Post post) {
        if(post.getId() == null) {
            post.setGmtCreate(System.currentTimeMillis());
            post.setGmtModified(post.getGmtCreate());
            postMapper.insert(post);
        } else {
            Post updatepost = new Post();
            updatepost.setGmtModified(System.currentTimeMillis());
            updatepost.setTitle(post.getTitle());
            updatepost.setTag(post.getTag());
            updatepost.setDescription(post.getDescription());
            PostExample postExample = new PostExample();
            postExample.createCriteria().andIdEqualTo(post.getId());
            int updated = postMapper.updateByExampleSelective(updatepost, postExample);
            if (updated != 1) {
                throw new CustomizeHardException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

    public void addViewCount(Long id) {
        Post raw_post = postMapper.selectByPrimaryKey(id);
        Post post = new Post();
        post.setViewCount(raw_post.getViewCount() + 1);
        PostExample postExample = new PostExample();
        postExample.createCriteria().andIdEqualTo(id);
        postMapper.updateByExampleSelective(post,postExample);
    }

    public void addCommentCount(Long id) {
        Post raw_post = postMapper.selectByPrimaryKey(id);
        Post post = new Post();
        post.setCommentCount(raw_post.getCommentCount() + 1);
        PostExample postExample = new PostExample();
        postExample.createCriteria().andIdEqualTo(id);
        postMapper.updateByExampleSelective(post,postExample);
    }
}
package top.renhongchen.community.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.renhongchen.community.model.Post;

import java.util.List;

@Mapper
public interface PostMapper {
    @Insert("insert into post (title,description,tag,creator,gmt_create,gmt_modified)" +
            "value (#{title},#{description},#{tag},#{creator},#{gmtCreate},#{gmtModified})")
    void insert(Post post);

    @Select("select * from post")
    List<Post> list();
}

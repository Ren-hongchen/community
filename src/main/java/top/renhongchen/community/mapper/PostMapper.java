package top.renhongchen.community.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import top.renhongchen.community.model.Post;

@Mapper
public interface PostMapper {
    @Insert("insert into posts (title,description,tag,creator,gmt_create,gmt_modified,comment_count,view_count,like_count)" +
            "value (#{title},#{description},#{tag},#{creator},#{gmt_create},#{gmt_modified},#{comment_count},#{view_count},#{like_count})")
    void insert(Post post);
}

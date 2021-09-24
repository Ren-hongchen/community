package top.renhongchen.community.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.renhongchen.community.dto.PostDTO;
import top.renhongchen.community.model.Post;

import java.util.List;

@Mapper
public interface PostMapper {
    @Insert("insert into post (title,description,tag,creator,gmt_create,gmt_modified)" +
            "value (#{title},#{description},#{tag},#{creator},#{gmtCreate},#{gmtModified})")
    void insert(Post post);

    @Select("select * from post limit #{offset},#{size}")
    List<Post> list(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);


    @Select("select count(1) from post")
    Integer count();   //统计表中行数

    @Select("select * from post where creator = #{id} limit #{offset},#{size}")
    List<Post> listById(@Param(value = "id") Integer id,
                        @Param(value = "offset") Integer offset,
                        @Param(value = "size") Integer size);

    @Select("select count(1) from post where creator = #{id}")
    Integer countById(@Param(value = "id") Integer id);

    @Select("select * from post where id = #{id}")
    Post getById(@Param(value = "id") Integer id);

}

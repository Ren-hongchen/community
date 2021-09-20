package top.renhongchen.community.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.renhongchen.community.model.User;

@Mapper
public interface UserMapper {
    @Select("select * from user where TKOEN = #{token}")
    User findByToken(@Param("token") String token);

    @Insert("insert into user (NAME,ACCOUNT_ID,TKOEN,GMT_CREATE,GMT_MODIFIED,avatar_url) " +
            "values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);
}

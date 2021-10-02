package top.renhongchen.community.mapper;


import org.apache.ibatis.annotations.*;
import top.renhongchen.community.model.User;

@Mapper
public interface UserMapper {
    @Select("select * from user where TOKEN = #{token}")
    User findByToken(@Param("token") String token);

    @Insert("insert into user (NAME,ACCOUNT_ID,TOKEN,GMT_CREATE,GMT_MODIFIED,avatar_url) " +
            "values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);

    @Select("select * from user where ID = #{id}")
    User findById(@Param("id") Integer id);

    @Select("select * from user where ACCOUNT_ID = #{accountId}")
    User findByAccountId(@Param(value = "accountId") String accountId);

    @Update("update user set NAME=#{name},TOKEN=#{token},GMT_MODIFIED=#{gmtModified},avatar_url=#{avatarUrl}")
    void update(User dbUser);
}

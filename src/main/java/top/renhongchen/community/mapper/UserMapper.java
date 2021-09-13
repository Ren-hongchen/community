package top.renhongchen.community.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.renhongchen.community.model.User;

@Mapper
public interface UserMapper {
    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);

    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified) " +
            "values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
    void insert(User user);
}

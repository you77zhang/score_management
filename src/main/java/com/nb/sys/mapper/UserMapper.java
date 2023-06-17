package com.nb.sys.mapper;

import com.nb.sys.entity.IUser;
import com.nb.sys.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zp
 * @since 2023-06-13
 */
public interface UserMapper extends BaseMapper<User> {

    List<User> getList(String username, Integer pageFr, Integer pageSize);
    
    @Update("update user set password = #{password} where id = #{id} and username = #{username};")
    Integer update(User user);

    @Insert("insert into user (username, password, role) values (#{username},#{password},#{role});")
    Integer add(IUser user);

    @Delete("delete from user where username = #{username};")
    Integer delete(User user);

    @Select("select * from user where username = #{username};")
    User getOne(String username);

    @Select("select * from user where username = #{username} and password = #{password};")
    Integer login(String username, String password);
    
    @Select("select role from user where username = #{username};")
    List<String> getRoles(String username);
}

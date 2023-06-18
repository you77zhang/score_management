package com.nb.sys.mapper;

import com.nb.sys.entity.Teacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.net.InterfaceAddress;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zp
 * @since 2023-06-13
 */
public interface TeacherMapper extends BaseMapper<Teacher> {

    List<Teacher> getList(String username, String name, Integer pageFr, Integer pageSize);

    @Update("update teacher set name=#{name},`desc`=#{desc}  where username=#{username};")
    Integer updateTea(Teacher tea);
    
    @Insert("insert into teacher(username, name, `desc`) value (#{username},#{name},#{desc});")
    Integer add(Teacher tea);

    @Delete("delete from teacher where username = #{username};")
    Integer remove(String username);

    @Select("select * from teacher where username = #{username};")
    List<Teacher> getOne(String username);
}

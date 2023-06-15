package com.nb.sys.mapper;

import com.nb.sys.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
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
public interface StudentMapper extends BaseMapper<Student> {

    List<Student> getList(String username, String name, String classes, Integer pageFr, Integer pageSize);
    @Update("update student set username=#{username},classes=#{classes},name=#{name}," +
            "score=#{score},`desc`=#{desc}  where id=#{id};")
    int updateStu(Student Stu);

    @Insert("insert into student (username, classes, name, score, `desc`)  values (#{username},#{classes},#{name},#{score},#{desc});")
    int addStu(Student stu);
}

package com.nb.sys.service;

import com.nb.sys.entity.Student;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zp
 * @since 2023-06-13
 */

public interface IStudentService extends IService<Student> {

    List<Student> getList(String username, String name, String classes, Integer pageNo, Integer pageSize);

    boolean updateStu(Student stu);

    boolean add(Student stu);

    List<Student> getOne(String username);
}

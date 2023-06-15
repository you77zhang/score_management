package com.nb.sys.service.impl;

import com.nb.sys.entity.Student;
import com.nb.sys.mapper.StudentMapper;
import com.nb.sys.service.IStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zp
 * @since 2023-06-13
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

    @Override
    public List<Student> getList(String username, String name, String classes, Integer pageNo, Integer pageSize) {
        Integer pageFr = (pageNo - 1)*pageSize;
        if("".equals(username)) username = null;
        if("".equals(name)) name = null;
        if("".equals(classes)) classes = null;
        return baseMapper.getList(username,name,classes,pageFr,pageSize);
    }

    @Override
    public boolean updateStu(Student stu) {
        return baseMapper.updateStu(stu) > 0;
    }

    @Override
    public boolean add(Student stu) {
        return baseMapper.addStu(stu) > 0;
    }
}

package com.nb.sys.service.impl;

import com.nb.sys.entity.Teacher;
import com.nb.sys.mapper.TeacherMapper;
import com.nb.sys.service.ITeacherService;
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
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements ITeacherService {

    @Override
    public List<Teacher> getList(String username, String name, Integer pageNo, Integer pageSize) {
        Integer pageFr = (pageNo - 1)*pageSize;
        if("".equals(username)) username = null;
        if("".equals(name)) name = null;
        return baseMapper.getList(username,name,pageFr,pageSize);
    }

    @Override
    public List<Teacher> getOne(String username) {
        return baseMapper.getOne(username);
    }

    @Override
    public boolean updateTea(Teacher tea) {
        return baseMapper.updateTea(tea) > 0;
    }

    @Override
    public boolean add(Teacher tea) {
        return baseMapper.add(tea) > 0;
    }

    @Override
    public boolean remove(String username) {
        return baseMapper.remove(username) > 0;
    }
}

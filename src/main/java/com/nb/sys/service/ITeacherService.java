package com.nb.sys.service;

import com.nb.sys.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zp
 * @since 2023-06-13
 */

public interface ITeacherService extends IService<Teacher> {

    List<Teacher> getList(String username, String name, Integer pageNo, Integer pageSize);
    List<Teacher> getOne(String username);
    
    boolean updateTea(Teacher tea);

    boolean add(Teacher tea);

    boolean remove(String username);
}

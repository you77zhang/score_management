package com.nb.sys.service;

import com.nb.sys.entity.IUser;
import com.nb.sys.entity.User;
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

public interface IUserService extends IService<User> {

    List<User> getList(String username,Integer pageNo, Integer pageSize);

    boolean update(User user);

    boolean add(IUser user);

    boolean delete(User user);

    User getOne(String username);

    boolean login(String username, String password);
}

package com.nb.sys.service.impl;

import com.nb.sys.entity.IUser;
import com.nb.sys.entity.User;
import com.nb.sys.mapper.UserMapper;
import com.nb.sys.service.IUserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Override
    public List<User> getList(String username, Integer pageNo, Integer pageSize) {
        Integer pageFr = (pageNo - 1)*pageSize;
        if("".equals(username)) username = null;
        return baseMapper.getList(username,pageFr,pageSize);
    }

    @Override
    public boolean update(User user) {
        return baseMapper.update(user)  > 0;
    }

    @Override
    public boolean add(IUser user) {
        return baseMapper.add(user) > 0;
    }

    @Override
    public boolean delete(User user) {
        return baseMapper.delete(user) > 0;
    }

    @Override
    public User getOne(String username) {
        return baseMapper.getOne(username);
    }

    @Override
    public boolean login(String username, String password) {
        return baseMapper.login(username,password) > 0;
    }
}

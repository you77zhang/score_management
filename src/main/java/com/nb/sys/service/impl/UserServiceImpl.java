package com.nb.sys.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.nb.sys.entity.IUser;
import com.nb.sys.entity.User;
import com.nb.sys.mapper.UserMapper;
import com.nb.sys.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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
    @Autowired
    private RedisTemplate redisTemplate;
    
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

    @Override
    public Map<String, Object> login(User user) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,user.getUsername());
        User loginUser = this.baseMapper.selectOne(wrapper);
        // 结果不为空，并且密码和传入密码匹配，则生成token，并将用户信息存入redis

        // TODO 改了passwordEncoder
        if(loginUser != null && loginUser.getPassword().equals(user.getPassword())){
            // 暂时用UUID, 终极方案是jwt
            String key = "user:" + UUID.randomUUID();

            // 存入redis
            loginUser.setPassword(null);
            redisTemplate.opsForValue().set(key,loginUser,30, TimeUnit.MINUTES);

            // 返回数据
            Map<String, Object> data = new HashMap<>();
            data.put("token",key);
            return data;
        }
        return null;
    }

    @Override
    public Map<String, Object> getUserInfo(String token) {
        //根据token获取用户信息
        Object obj = redisTemplate.opsForValue().get(token);
        if(obj != null){
            User user = JSON.parseObject(JSON.toJSONString(obj), User.class);
            Map<String,Object> data = new HashMap<>();
            data.put("username",user.getUsername());
            List<String> roles = baseMapper.getRoles(user.getUsername());
            data.put("roles",roles);
            return data;
        }
        return null;
    }
}

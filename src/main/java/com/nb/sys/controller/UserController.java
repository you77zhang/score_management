package com.nb.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.nb.common.vo.Result;
import com.nb.sys.entity.*;
import com.nb.sys.service.IStudentService;
import com.nb.sys.service.ITeacherService;
import com.nb.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zp
 * @since 2023-06-13
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private ITeacherService teacherService;
    @Autowired
    private IStudentService studentService;
    @GetMapping("/list")
    public Result<Map<String,Object>> getList(@RequestParam(value = "username",required = false) String username,
                                              @RequestParam(value = "pageNo") Integer pageNo,
                                              @RequestParam(value = "pageSize") Integer pageSize){
        List<User> userList = userService.getList(username,pageNo,pageSize);
        Map<String,Object> data = new HashMap<>();
        data.put("data",userList);
        return Result.success(data);
    }

    @GetMapping("/getByUsername")
    public Result<Map<String,Object>> getOne(@RequestParam(value = "username",required = false) String username){
        User one = userService.getOne(username);
        Map<String,Object> data = new HashMap<>();
        data.put("data",one);
        return Result.success(data);
    }
    
    @PostMapping("/update")
    public Result<?> update(@RequestBody User user){
        userService.update(user);
        return Result.success();
    }
    
    @PostMapping("/add")
    public Result<?> add(@RequestBody IUser user){
        userService.add(user);
        if(user.getRole().equals(Role.Teacher)){
            Teacher tea = new Teacher();
            tea.setUsername(user.getUsername());
            tea.setName(user.getName());
            teacherService.add(tea);
        }
        else if(user.getRole().equals(Role.Student)){
            Student stu = new Student();
            stu.setUsername(user.getUsername());
            stu.setName(user.getName());
            studentService.add(stu);
        }
        return Result.success();
    }
    @PostMapping("/delete")
    public Result<?> delete(@RequestBody User user){
        User one = userService.getOne(user.getUsername());
        userService.delete(user);
        
        if(one.getRole().equals(Role.Teacher)){
            teacherService.remove(user.getUsername());
        }
        else if(one.getRole().equals(Role.Student)){
            LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Student::getUsername,user.getUsername());
            studentService.remove(wrapper);
        }
        return Result.success("删除成功");
    }
    
    @PostMapping("/login")
    public Result<Map<String,Object>> login(@RequestBody User user){
        Map<String,Object> data = userService.login(user);
        if(data != null){
            return Result.success(data);
        }
        else return Result.fail(20002,"账号或密码错误");
    }

    @GetMapping("/info")
    public Result<Map<String,Object>> getInfo(@RequestParam("token") String token){
        Map<String,Object> data = userService.getUserInfo(token);
        if(data != null){
            return Result.success(data);
        }
        return Result.fail(20003,"登录信息无效，请重新登录");
    }

    @PostMapping("/logout")
    public Result<?> logout(@RequestHeader("X-Token") String token){
        // userService.logout(token);
        return Result.success();
    }
    
    @PostMapping("/updatePwd")
    public Result<?> logout(@RequestBody Pwd pwd){
        User one = userService.getOne(pwd.getUsername());
        if(one == null){
            return Result.fail("不存在该用户");
        }
        else if(!one.getPassword().equals(pwd.getPrePassword())){
            return Result.fail("请输入正确的密码");
        }
        else{
            one.setPassword(pwd.getNewPassword());
            userService.update(one);
            return Result.success();
        }
    }
}

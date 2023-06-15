package com.nb.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nb.common.vo.Result;
import com.nb.sys.entity.Student;
import com.nb.sys.service.IStudentService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private IStudentService studentService;

    @GetMapping("/list")
    // @PostMapping("/query")
    public Result<Map<String,Object>> getList(@RequestParam(value = "username",required = false) String username,
                                                @RequestParam(value = "name",required = false) String name,
                                                @RequestParam(value = "classes",required = false) String classes,
                                                @RequestParam(value = "pageNo") Integer pageNo,
                                                @RequestParam(value = "pageSize") Integer pageSize){
        List<Student> students  = studentService.getList(username,name,classes,pageNo,pageSize);
        // long total  = studentService.getListCount(username,name,classes);

        Map<String,Object> data = new HashMap<>();
        data.put("data",students);
        return Result.success(data);
    }

    @PostMapping("/addStudent")
    public Result<?> addStu(@RequestBody Student stu){
        
        studentService.add(stu);
        return Result.success("新增用户成功");
    }

    @DeleteMapping("/{username}")
    public Result<?> deleteStu(@PathVariable String username){
        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Student::getUsername,username);
        studentService.remove(wrapper);
        return Result.success("删除成功");
    }

    @PutMapping()
    public Result<?> updateSty(@RequestBody Student stu){
        studentService.updateStu(stu);
        return Result.success("修改成功");
    }
    
}

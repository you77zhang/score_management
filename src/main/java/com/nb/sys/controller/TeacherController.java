package com.nb.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.nb.common.vo.Result;
import com.nb.sys.entity.Teacher;
import com.nb.sys.service.ITeacherService;
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
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private ITeacherService teacherService;

    @GetMapping("/list")
    public Result<Map<String,Object>> getList(@RequestParam(value = "username",required = false) String username,
                                              @RequestParam(value = "name",required = false) String name,
                                              @RequestParam(value = "pageNo") Integer pageNo,
                                              @RequestParam(value = "pageSize") Integer pageSize){
        List<Teacher> teachers  = teacherService.getList(username,name,pageNo,pageSize);

        Map<String,Object> data = new HashMap<>();
        data.put("data",teachers);
        return Result.success(data);
    }

    @PostMapping("/addTeacher")
    public Result<?> addTea(@RequestBody Teacher tea){
        teacherService.add(tea);
        return Result.success("新增成功");
    }

    @DeleteMapping("/{username}")
    public Result<?> deleteTea(@PathVariable String username){
        teacherService.remove(username);
        return Result.success("删除成功");
    }

    @PutMapping()
    public Result<?> updateSty(@RequestBody Teacher tea){
        teacherService.updateTea(tea);
        return Result.success("修改成功");
    }

    @GetMapping("/getOne")
    public Result<Map<String,Object>> getOne(@RequestParam(value = "username",required = false) String username){
        List<Teacher> teacher  = teacherService.getOne(username);

        Map<String,Object> data = new HashMap<>();
        data.put("data",teacher);
        return Result.success(data);
    }
}

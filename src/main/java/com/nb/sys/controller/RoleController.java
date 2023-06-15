package com.nb.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nb.common.vo.Result;
import com.nb.sys.entity.Student;
import com.nb.sys.service.IRoleService;
import com.nb.sys.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
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
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService service;
    
}

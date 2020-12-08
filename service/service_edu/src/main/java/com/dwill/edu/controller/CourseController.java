package com.dwill.edu.controller;


import com.dwill.commonutils.R;
import com.dwill.edu.entity.vo.CourseInfoVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author dwill
 * @since 2020-12-08
 */
@RestController
@RequestMapping("/edu/course")
@CrossOrigin
public class CourseController {
    @ApiOperation(value = "添加课程基本信息")
    @PostMapping("/addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){

    }
}


package com.dwill.edu.controller;


import com.dwill.commonutils.R;
import com.dwill.edu.entity.subject.OneSubject;
import com.dwill.edu.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author dwill
 * @since 2020-11-12
 */
@RestController
@RequestMapping("/edu/subject")
@CrossOrigin
public class SubjectController {
    @Autowired
    private SubjectService subjectService;
    //添加课程分类
    //获取上传过来的文件，把文件内容读取出来
    @PostMapping("addSubject")
    public R addSubject(MultipartFile file){
        subjectService.saveSubject(file,subjectService);
        return R.ok();
    }
    //课程分类列表
    @GetMapping("/getAllSubject")
    public R getAllSubject(){
        List<OneSubject> list = subjectService.getAllOeTwoSubject();
        return R.ok().data("list",list);
    }
}


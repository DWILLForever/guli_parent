package com.dwill.edu.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dwill.commonutils.R;
import com.dwill.edu.entity.Teacher;
import com.dwill.edu.entity.vo.TeacherQuery;
import com.dwill.edu.service.TeacherService;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-11-02
 */
@RestController
@RequestMapping("/edu/teacher")
@CrossOrigin  //解决跨域问题
public class TeacherController {
    //把service注入
    @Autowired
    private TeacherService teacherService;
    //查询讲师表所有数据rest风格命名
    //接口文档加入中文提示
    @ApiOperation(value = "查询所有讲师")
    @GetMapping("/findAll")
    public R findAllTeacher(){
        //调用service的方法
        List<Teacher> list = teacherService.list(null);
        return R.ok().data("items",list);
    }
    //逻辑删除讲师的方法,required = false允许为空
    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("{id}")
    public R removeTeacher(@ApiParam(name = "id",value = "讲师的ID",required = false) @PathVariable String id){
        boolean flag=teacherService.removeById(id);
        if(flag){
            return R.ok();
        }else {
            return R.error();
        }
    }
    /**
     * 分页查询讲师的方法
     * current当前页
     * limit每页记录数
     */
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageListTeacher(@PathVariable long current,
                             @PathVariable long limit) {
        //创建page对象
        Page<Teacher> pageTeacher = new Page<>(current,limit);
        System.out.println(pageTeacher);
        //调用方法实现分页
        //调用方法时候，底层封装，把分页所有数据封装到pageTeacher对象里面
        teacherService.page(pageTeacher,null);

        long total = pageTeacher.getTotal();//总记录数
        List<Teacher> records = pageTeacher.getRecords(); //数据list集合

//        Map map = new HashMap();
//        map.put("total",total);
//        map.put("rows",records);
//        return R.ok().data(map);
        System.out.println(total);
        return R.ok().data("total",total).data("rows",records);
    }


    //4 条件查询带分页的方法
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current,@PathVariable long limit,
                                     @RequestBody(required = false)  TeacherQuery teacherQuery
                                  ) {
        //创建page对象
        Page<Teacher> pageTeacher = new Page<>(current,limit);

        //构建条件
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        // mybatis学过 动态sql
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(name)) {
            //构建条件
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(level)) {
            wrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create",end);
        }

        //排序
        wrapper.orderByDesc("gmt_create");

        //调用方法实现条件查询分页
        teacherService.page(pageTeacher,wrapper);

        long total = pageTeacher.getTotal();//总记录数
        List<Teacher> records = pageTeacher.getRecords(); //数据list集合
        return R.ok().data("total",total).data("rows",records);
    }



    //根据讲师id进行查询
    @GetMapping("getTeacher/{id}")
    public R getTeacher(@PathVariable String id) {
        Teacher eduTeacher = teacherService.getById(id);
        return R.ok().data("teacher",eduTeacher);
    }
    //添加讲师
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody Teacher teacher){
        boolean save = teacherService.save(teacher);
        if(save){
            return R.ok();
        }else {
            return R.error();
        }
    }
    //讲师修改功能
    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody Teacher teacher){
        boolean flag = teacherService.updateById(teacher);
        if(flag){
            return R.ok();
        }else {
            return R.error();
        }
    }



}


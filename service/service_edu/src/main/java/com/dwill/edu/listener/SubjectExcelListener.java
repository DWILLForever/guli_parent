package com.dwill.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dwill.edu.entity.Subject;
import com.dwill.edu.entity.excel.SubjectData;
import com.dwill.edu.service.SubjectService;
import com.dwill.servicebase.exceptionhandler.GuliException;

public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {
   //因为SubjectExcelListener不能交给spring进行管理，需要自己new一个对象，不能注入其它对象
   //否则实现不了数据库的操作
    public SubjectService subjectService;

    public SubjectExcelListener() {
    }
    public SubjectExcelListener(SubjectService subjectService) {
        this.subjectService = subjectService;
    }
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
      if(subjectData==null){
         throw new GuliException(20001,"文件数据为空");
      }
      //一行一行读取，每次读取有两个值，第一个值一级分类，第二个值是二级分类
     //判断一级分类是否重复
      Subject existOneSubject=this.existOneSubject(subjectService,subjectData.getOneSubjectName());
      if(existOneSubject==null){
        existOneSubject=new Subject();//如果一级目录不存在就新建，存在就不需要操作
        existOneSubject.setParentId("0");//一级目录默认设为0
        existOneSubject.setTitle(subjectData.getOneSubjectName());//设置一级目录的名字
        subjectService.save(existOneSubject);//将一级目录放入数据库
      }
      //获取一级分类的id
      String pid=existOneSubject.getId();
      //判断二级分类是否重复
     Subject existTwoSubject=this.existTwoSubject(subjectService,subjectData.getTwoSubjectName(),pid);
     if(existTwoSubject==null){
      existTwoSubject=new Subject();//如果二级目录不存在，就新建，存在就不需要操作
      existTwoSubject.setTitle(subjectData.getTwoSubjectName());//设置二级目录的名字
      existTwoSubject.setParentId(pid);//将一级目录的id二级目录的父id 中
      subjectService.save(existTwoSubject);//将数据保存进数据库
     }

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
    //判断一级分类是否已存在，不能重复添加
    private Subject existOneSubject(SubjectService subjectService,String name){
      QueryWrapper<Subject> wrapper=new QueryWrapper<>();
      wrapper.eq("title",name);//设置目录的名字
      wrapper.eq("parent_id","0");//设置目录的父目录的id=0
      Subject oneSubject=subjectService.getOne(wrapper);//声明这个课程
      return oneSubject;
    }
    //判断二级分类是否存在，不能重复添加
   private Subject existTwoSubject(SubjectService subjectService,String name,String pid){
     QueryWrapper<Subject> wrapper=new QueryWrapper<>();
     wrapper.eq("title",name);
     wrapper.eq("parent_id",pid);
     Subject twoSubject=subjectService.getOne(wrapper);
     return twoSubject;
   }
}

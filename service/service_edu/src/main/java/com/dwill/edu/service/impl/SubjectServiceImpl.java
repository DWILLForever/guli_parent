package com.dwill.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dwill.edu.entity.Subject;
import com.dwill.edu.entity.excel.SubjectData;
import com.dwill.edu.entity.subject.OneSubject;
import com.dwill.edu.entity.subject.TwoSubject;
import com.dwill.edu.mapper.SubjectMapper;
import com.dwill.edu.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dwill.edu.listener.SubjectExcelListener;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author dwill
 * @since 2020-11-12
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Override
    public void saveSubject(MultipartFile file, SubjectService subjectService) {
        try {
            //文件输入流
            InputStream in=file.getInputStream();
            //调用方法进行读取
            EasyExcel.read(in,SubjectData.class,new SubjectExcelListener(subjectService)).sheet().doRead();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<OneSubject> getAllOeTwoSubject() {
        //获取一级分类，依据是parent_id=0
        QueryWrapper wrapperOne=new QueryWrapper();
        wrapperOne.eq("parent_id","0");
        List<Subject> oneSubjectList =baseMapper.selectList(wrapperOne);
        //获取二级分类，依据是parent_id！=0
        QueryWrapper wrapperTwo=new QueryWrapper();
        wrapperTwo.ne("parent_id","0");
        List<Subject> twoSubjectList=baseMapper.selectList(wrapperTwo);
        List<OneSubject> finalOneSubjects=new ArrayList<>();
        for (int i = 0; i <oneSubjectList.size() ; i++) {
            Subject oSubject=oneSubjectList.get(i);
            OneSubject oneSubject=new OneSubject();
            BeanUtils.copyProperties(oSubject,oneSubject);
            finalOneSubjects.add(oneSubject);
            List<TwoSubject> finalTwoSubject=new ArrayList<>();
            for (int j = 0; j <twoSubjectList.size() ; j++) {
                Subject tSubject=twoSubjectList.get(j);
                TwoSubject twoSubject=new TwoSubject();
                BeanUtils.copyProperties(tSubject,twoSubject);
                if (tSubject.getParentId().equals(oneSubject.getId())){
                    finalTwoSubject.add(twoSubject);
                }
            }
            oneSubject.setChildren(finalTwoSubject);
        }
        return finalOneSubjects;
    }
}

package com.dwill.edu.service;

import com.dwill.edu.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dwill.edu.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author dwill
 * @since 2020-11-12
 */
public interface SubjectService extends IService<Subject> {
    //添加课程
    void saveSubject(MultipartFile file, SubjectService subjectService);
    public List<OneSubject> getAllOeTwoSubject();
}

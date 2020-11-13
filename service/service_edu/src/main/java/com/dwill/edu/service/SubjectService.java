package com.dwill.edu.service;

import com.dwill.edu.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

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
}

package com.dwill.edu.service.impl;

import com.dwill.edu.entity.Course;
import com.dwill.edu.mapper.CourseMapper;
import com.dwill.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author dwill
 * @since 2020-12-08
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

}

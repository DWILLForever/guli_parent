package com.dwill.edu.service.impl;

import com.dwill.edu.entity.CourseDescription;
import com.dwill.edu.mapper.CourseDescriptionMapper;
import com.dwill.edu.service.CourseDescriptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程简介 服务实现类
 * </p>
 *
 * @author dwill
 * @since 2020-12-08
 */
@Service
public class CourseDescriptionServiceImpl extends ServiceImpl<CourseDescriptionMapper, CourseDescription> implements CourseDescriptionService {

}

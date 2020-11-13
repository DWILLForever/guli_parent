package com.dwill.edu.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.Serializable;

/**
 *
 * 传递查询条件
 */
@Data
public class TeacherQuery implements Serializable {
    @ApiModelProperty(value = "教师名称，模糊查询")
    private String name;
    @ApiModelProperty(value = "头衔 1 高级讲师 2 首席讲师")
    private Integer level;
    @ApiModelProperty(value = "查询开始时间",example = "2020-11-11 10:10:10")
    private String begin;//注意，这里使用的String类型，前端传过来的数据无需再转换
    @ApiModelProperty(value = "查询结束时间",example = "2020-11-11 10:11:10")
    private String end;
}

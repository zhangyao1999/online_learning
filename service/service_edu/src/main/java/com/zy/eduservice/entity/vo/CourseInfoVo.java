package com.zy.eduservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author ZY
 * @create 2022/2/15 14:38
 */
@Data
public class CourseInfoVo {


    @ApiModelProperty(value = "课程ID")
    private String id;

    @ApiModelProperty(value = "课程讲师ID")
    private String teacherId;

    @ApiModelProperty(value = "课程专业ID")
    private String subjectId;

    @ApiModelProperty(value = "一级分类ID")
    private String subjectParentId;

    @ApiModelProperty(value = "课程标题")
    @NotNull(message = "标题不能为空")
    @NotBlank(message = "标题不能为空")
    private String title;

    @ApiModelProperty(value = "课程销售价格，设置为0则可免费观看")
    @Min(message = "请输入正确的价格", value = 0)
    private BigDecimal price;

    @ApiModelProperty(value = "总课时")
    @Min(message = "请输入课时数", value = 1)
    private Integer lessonNum;

    @ApiModelProperty(value = "课程封面图片路径")
    private String cover;

    @ApiModelProperty(value = "课程简介")
    @NotNull(message = "简介不能为空")
    @NotBlank(message = "简介不能为空")
    private String description;
}

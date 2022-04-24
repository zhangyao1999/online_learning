package com.zy.eduservice.entity.vo;

import lombok.Data;

/**
 * @author ZY
 * @create 2022/4/24 15:35
 */
@Data
public class CoursePublishVo {

    private String id;

    private String title;

    private String cover;

    private Integer lessonNum;

    private String subjectLevelOne;

    private String subjectLevelTwo;

    private String teacherName;

    private String price;
}


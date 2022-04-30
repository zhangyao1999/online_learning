package com.zy.eduservice.entity.chapter;

/**
 * @author ZY
 * @create 2022/3/4 15:31
 */

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;


@ApiModel(value = "课时信息")
@Data
public class VideoVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private Boolean free;
    private String videoSourceId;//视频id
}
package com.zy.eduservice.entity.eduSubject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZY
 * @create 2022/2/14 11:03
 */
@Data
public class OneSubject {
    private String id;
    private String title;

    private List<TwoSubject> children;
}

package com.zy.eduservice.service;

import com.zy.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.eduservice.entity.eduSubject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author zy
 * @since 2022-01-18
 */

public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile file);

    List<OneSubject> getSubjectList();
}

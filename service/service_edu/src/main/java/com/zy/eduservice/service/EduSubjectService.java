package com.zy.eduservice.service;

import com.zy.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
}

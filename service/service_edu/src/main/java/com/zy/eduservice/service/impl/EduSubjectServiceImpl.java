package com.zy.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.zy.eduservice.entity.EduSubject;
import com.zy.eduservice.entity.excel.SubjectData;
import com.zy.eduservice.listener.SubjectExcelListener;
import com.zy.eduservice.mapper.EduSubjectMapper;
import com.zy.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author zy
 * @since 2022-01-18
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(), SubjectData.class,new SubjectExcelListener(this)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

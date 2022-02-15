package com.zy.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.mysql.cj.log.Log;
import com.zy.eduservice.entity.EduSubject;
import com.zy.eduservice.entity.eduSubject.OneSubject;
import com.zy.eduservice.entity.eduSubject.TwoSubject;
import com.zy.eduservice.entity.excel.SubjectData;
import com.zy.eduservice.listener.SubjectExcelListener;
import com.zy.eduservice.mapper.EduSubjectMapper;
import com.zy.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author zy
 * @since 2022-01-18
 */
@Service
@Slf4j
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(), SubjectData.class,new SubjectExcelListener(this)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OneSubject> getSubjectList() {
        QueryWrapper<EduSubject> oneSubjectWrapper = new QueryWrapper<>();
        oneSubjectWrapper.eq("parent_id","0");
        List<EduSubject> eduSubjects1 = baseMapper.selectList(oneSubjectWrapper);
        log.info("list"+eduSubjects1);
//        System.out.println("list"+eduSubjects1.toString());
        QueryWrapper<EduSubject> twoSubjectWrapper = new QueryWrapper<>();
        oneSubjectWrapper.ne("parent_id","0");
        List<EduSubject> eduSubjects2 = baseMapper.selectList(twoSubjectWrapper);
        log.info("list2",eduSubjects2.toArray().toString());
        List<OneSubject> oneSubjectList = new ArrayList<>();

        for (int i = 0; i < eduSubjects1.size(); i++) {
            EduSubject eduSubject = eduSubjects1.get(i);
            OneSubject oneSubject = new OneSubject();
            BeanUtils.copyProperties(eduSubject,oneSubject);

            ArrayList<TwoSubject> twoSubjects = new ArrayList<>();
            for (int k = 0; k < eduSubjects2.size(); k++) {
                EduSubject eduSubject1 = eduSubjects2.get(k);
                if(eduSubject.getId().equals(eduSubject1.getParentId())){
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(eduSubject1,twoSubject);
                    twoSubjects.add(twoSubject);
                }
            }
            oneSubject.setChildren(twoSubjects);
            oneSubjectList.add(oneSubject);
        }

        return oneSubjectList;
    }
}

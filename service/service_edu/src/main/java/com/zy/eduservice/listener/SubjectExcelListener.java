package com.zy.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zy.commonutils.ResultCode;
import com.zy.eduservice.entity.EduSubject;
import com.zy.eduservice.entity.excel.SubjectData;
import com.zy.eduservice.service.EduSubjectService;
import com.zy.servicebase.config.ExceptionHandler.MyException;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * @author ZY
 * @create 2022/1/20 16:41
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {
    public EduSubjectService eduSubjectService;

    public SubjectExcelListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    public SubjectExcelListener() {
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if(subjectData == null){
            throw new MyException(ResultCode.ERROR,"excel表为空");
        }
        EduSubject geteduSubject = this.findOneRepeat(subjectData.getOneSubjectName());
        if (geteduSubject == null){
            geteduSubject = new EduSubject();
            geteduSubject.setParentId("0");
            geteduSubject.setTitle(subjectData.getOneSubjectName());
            eduSubjectService.save(geteduSubject);
        }
        String pid = geteduSubject.getId();
        EduSubject geteduSubject2 = this.findTwoRepeat(subjectData.getTwoSubjectName(), pid);
        if (geteduSubject2 == null){
            EduSubject eduSubject2 = new EduSubject();
            eduSubject2.setParentId(pid);
            eduSubject2.setTitle(subjectData.getTwoSubjectName());
            eduSubjectService.save(eduSubject2);
        }

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
    private EduSubject findOneRepeat(String name){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("title",name);
        queryWrapper.eq("parent_id","0");
        EduSubject eduSubject = eduSubjectService.getOne(queryWrapper);
        return eduSubject;
    }
    private EduSubject findTwoRepeat(String name,String pid){
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("title",name);
        queryWrapper.eq("parent_id",pid);
        EduSubject eduSubject = eduSubjectService.getOne(queryWrapper);
        return eduSubject;
    }

}

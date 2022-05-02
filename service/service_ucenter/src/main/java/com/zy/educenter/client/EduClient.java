package com.zy.educenter.client;

import com.zy.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

/**
 * @author ZY
 * @create 2022/5/1 16:01
 */
@FeignClient(name = "service-edu")
//, fallback = VodFileDegradeFeignClient.class
//要调用得服务端名字
@Component
public interface EduClient {
    @GetMapping("/eduservice/edu-course/getCoursePrice/{courseId}")
    public com.zy.commonutils.CourseInfoVo getCoursePrice(@PathVariable String courseId);
}

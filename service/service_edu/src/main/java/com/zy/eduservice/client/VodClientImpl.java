package com.zy.eduservice.client;

import com.zy.commonutils.R;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ZY
 * @create 2022/4/25 19:51
 */
@Component
public class VodClientImpl implements VodClient{
    @Override
    public R removeAliyunVideo(String id) {
        return R.error().message("删除视频失败");
    }

    @Override
    public R deleteBatch(List<String> videoIdList) {
        return R.error().message("删除多个视频失败");
    }
}

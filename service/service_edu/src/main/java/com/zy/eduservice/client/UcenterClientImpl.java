package com.zy.eduservice.client;

import com.zy.commonutils.R;
import com.zy.commonutils.ResultCode;
import com.zy.servicebase.config.ExceptionHandler.MyException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author ZY
 * @create 2022/4/30 19:50
 */
@Component
public class UcenterClientImpl implements UcenterClient{

    @Override
    public Map<String, String> getInfo(String id) {
        throw  new MyException(ResultCode.ERROR,"获取用户信息失败");
    }

}

package com.zy.eduservice.client;

import com.zy.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author ZY
 * @create 2022/4/30 19:47
 */
@FeignClient(name = "service-ucenter",fallback = UcenterClientImpl.class)
//, fallback = VodFileDegradeFeignClient.class
//要调用得服务端名字
@Component
public interface UcenterClient {
    @PostMapping("/educenter/member/getInfoUc/{id}")
    public Map<String,String> getInfo(@PathVariable String id) ;
}

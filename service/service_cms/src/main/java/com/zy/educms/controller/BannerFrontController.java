package com.zy.educms.controller;


import com.zy.commonutils.R;
import com.zy.educms.entity.CrmBanner;
import com.zy.educms.service.CrmBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author zy
 * @since 2022-04-26
 */
@RestController
@RequestMapping("/educms/bannerfront")
@CrossOrigin
public class BannerFrontController {

    @Autowired
    private CrmBannerService bannerService;

    //查询所有的banner
    @GetMapping("/getAllBanner")
    public R getAllBanner() {
        List<CrmBanner> list = bannerService.list(null);
        Set<CrmBanner> set = new HashSet<CrmBanner>();
        Random random = new Random();
        while (set.size()<=3){
            set.add(list.get(random.nextInt(list.size())));
        }
//        for (int i = 0 ;i<list.size();i++){
//            set.add(list.get(random.nextInt(list.size())));
//            if(set.size()>3){
//                break;
//            }
//        }
        return R.ok().data("list", set);
    }
}

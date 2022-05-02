package com.zy.educenter.controller;


import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zy.commonutils.CourseInfoVo;
import com.zy.commonutils.JwtUtils;
import com.zy.commonutils.R;
import com.zy.commonutils.ResultCode;
import com.zy.educenter.client.EduClient;
import com.zy.educenter.entity.Order;
import com.zy.educenter.entity.PayLog;
import com.zy.educenter.entity.UcenterMember;
import com.zy.educenter.service.OrderService;
import com.zy.educenter.service.PayLogService;
import com.zy.educenter.service.UcenterMemberService;
import com.zy.educenter.utils.OrderNoUtil;
import com.zy.servicebase.config.ExceptionHandler.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author zy
 * @since 2022-05-01
 */
@RestController
@RequestMapping("/educenter/order")
@CrossOrigin
@Slf4j
public class OrderController {

    @Autowired
    private PayLogService payLogService;
    @Autowired
    private EduClient eduClient;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UcenterMemberService memberService;

    @GetMapping("isBuyCourse/{courseid}")
    public R isBuyCourse(HttpServletRequest request,@PathVariable String courseid){
        log.error("request是"+request);
        String memberId = JwtUtils.getMemberIdByJwtToken(request);

        log.error("member"+memberId);
        //订单状态是1表示支付成功
        int count = orderService.count(new QueryWrapper<Order>().eq("member_id", memberId).eq("course_id", courseid).eq("status", 1));
        log.error("isbuy"+count);
        if(count>0) {
            return R.ok().data("isBuy",true);
        } else {
            return R.ok().data("isBuy",false);
        }
    }

    @GetMapping("queryPayStatus/{orderid}")
    public R QueryPayStatus (@PathVariable String orderid){
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("order_no",orderid);
        Order one = orderService.getOne(orderQueryWrapper);
        if (one.getStatus()==1){
            return R.ok().data("order",one);
        }else {
            return R.error();
        }

    }

    @PostMapping("/pay/{orderid}")
    public R Pay (@PathVariable String orderid) throws AlipayApiException {
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("order_no",orderid);
        Order order = orderService.getOne(orderQueryWrapper);
        order.setStatus(1);
        orderService.updateById(order);
        PayLog payLog = new PayLog();
        payLog.setOrderNo(order.getOrderNo());//支付订单号
        payLog.setPayTime(new Date());
        payLog.setPayType(1);//支付类型
        payLog.setTotalFee(order.getTotalFee());//总金额(分)
        payLogService.save(payLog);
        return R.ok();

    }

    @PostMapping ("/addorder/{courseid}")
    public R addOrder (HttpServletRequest request, @PathVariable String courseid){
//        log.error(""+);
        UcenterMember ucenterMember = new UcenterMember();

        try {
            //调用jwt的工具类方法,根据request对象获取头信息,返回用户id
            String memberId = JwtUtils.getMemberIdByJwtToken(request);
            //根据id查询用户信息
            ucenterMember= this.memberService.getById(memberId);
        }catch (Exception e){
            throw  new MyException(ResultCode.ERROR,"未登录");
        }
        Order order = new Order();
        order.setCourseId(courseid);
        order.setMemberId(ucenterMember.getId());
        order.setOrderNo(OrderNoUtil.getOrderNo());
        order.setStatus(0);
        CourseInfoVo course = eduClient.getCoursePrice(courseid);
        order.setCourseCover(course.getCover());
        order.setNickname(ucenterMember.getNickname());
        order.setCourseTitle(course.getTitle());
        order.setTotalFee(course.getPrice());
        orderService.save(order);
        return  R.ok().data("orderId",order.getOrderNo());
    }
    @GetMapping("getOrder/{orderId}")
    public R get(@PathVariable String orderId) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderId);
        Order order = orderService.getOne(wrapper);
        return R.ok().data("item", order);
    }


}


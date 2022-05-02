package com.zy.educenter.service.impl;

import com.zy.educenter.entity.Order;
import com.zy.educenter.mapper.OrderMapper;
import com.zy.educenter.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author zy
 * @since 2022-05-01
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}

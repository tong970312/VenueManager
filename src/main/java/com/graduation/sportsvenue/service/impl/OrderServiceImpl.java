package com.graduation.sportsvenue.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.graduation.sportsvenue.bean.Order;
import com.graduation.sportsvenue.bean.Venue;
import com.graduation.sportsvenue.common.ServiceResponse;
import com.graduation.sportsvenue.dao.OrderMapper;
import com.graduation.sportsvenue.dao.UserMapper;
import com.graduation.sportsvenue.dao.VenueMapper;
import com.graduation.sportsvenue.service.OrderService;
import com.graduation.sportsvenue.util.BigDecimalUtils;
import com.graduation.sportsvenue.util.DateUtil;
import com.graduation.sportsvenue.vo.OrderListVo;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;

    @Autowired
    VenueMapper venueMapper;

    @Autowired
    UserMapper userMapper;
    /**
     * 管理员查看所有订单
     *
     * @return orderList
     */
    @Override
    public ServiceResponse selectOrderList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Order> orderList = orderMapper.selectAll();
        if (orderList != null && orderList.size() > 0) {

            List<OrderListVo> orderListVos = getOrderVo(orderList);

            PageInfo pageInfo = new PageInfo(orderListVos);
            return ServiceResponse.createSuccessResponse("所有订单", pageInfo);
        }
        return ServiceResponse.createErrorResponse("查询失败");
    }

    /**
     * 管理员查看已完成订单
     *
     * @return payOrderList
     */
    @Override
    public ServiceResponse selectOrderPay(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Order> payOrderList = orderMapper.selectPayOrder();
        if (payOrderList != null && payOrderList.size() > 0) {
            List<OrderListVo> orderListVos = getOrderVo(payOrderList);
            PageInfo pageInfo = new PageInfo(orderListVos);
            return ServiceResponse.createSuccessResponse("已完成订单", pageInfo);
        }
        return ServiceResponse.createErrorResponse("查询失败");
    }

    /**
     * 用户查看自己的订单
     *
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public ServiceResponse userGetOrder(Integer userId, Integer pageNum, Integer pageSize) {
        if (userId == null) {
            return ServiceResponse.createErrorResponse("参数不能为空");
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Order> orderList = orderMapper.selectOrderByUserId(userId);
        if (orderList != null && orderList.size() > 0) {
            List<OrderListVo> orderListVos = getOrderVo(orderList);
            PageInfo pageInfo = new PageInfo(orderListVos);
            return ServiceResponse.createSuccessResponse("当前用户账单信息", pageInfo);
        }

        return ServiceResponse.createErrorResponse("查询失败");
    }

    /**
     * 预定场地
     *
     * @param userId
     * @param venueId
     * @param useTime
     * @return
     */
    @Override
    public ServiceResponse createOrder(Integer userId, Integer venueId, Integer useTime,String startTime) {
        if (userId == null || venueId == null || useTime == null || startTime == null) {
            return ServiceResponse.createErrorResponse("参数错误");
        }
        Venue venue = venueMapper.selectByVenueId(venueId);
        if (venue == null) {
            return ServiceResponse.createErrorResponse("当前场地已被占用或已删除");
        }
        //计算总价格
        BigDecimal payment = BigDecimalUtils.mul(venue.getPrice().doubleValue(), useTime);
        //生成订单号
        Long orderNo = System.currentTimeMillis() + new Random().nextInt(100);
        //生成订单
        Order order = createOrder(userId, venue, payment, orderNo, useTime ,startTime);

        if (order == null) {
            return ServiceResponse.createErrorResponse("预定场地失败");
        }

        int addResult = orderMapper.insert(order);
        if (addResult > 0) {
            Order order2 = orderMapper.selectByPrimaryKey(order.getId());

            return ServiceResponse.createSuccessResponse("预定场地成功", order2);
        }
        return ServiceResponse.createErrorResponse("预定场地失败");
    }

    /**
     * 生成预定场地订单
     *
     * @param userId
     * @param venue
     * @param payment
     * @param orderNo
     * @param useTime
     * @return
     */
    private Order createOrder(Integer userId, Venue venue, BigDecimal payment, Long orderNo, Integer useTime , String startTime) {
        Order order = new Order();
        order.setUserid(userId);
        order.setOrderNo(orderNo);
        order.setAreaid(venue.getId());
        order.setUsetime(useTime);
        order.setLocation(venue.getLocation());
        order.setPrice(venue.getPrice());
        order.setPayment(payment);
        order.setPaystatus(0);
        order.setStarttime(DateUtil.stringToDate(startTime));
        return order;
    }


    /**
     * 封装成OrderVo
     *
     * @param orders
     * @return
     */
    private List<OrderListVo> getOrderVo(List<Order> orders) {
        List<OrderListVo> orderListVos = Lists.newArrayList();
        for (Order order : orders) {
            OrderListVo listVo = new OrderListVo();
            listVo.setId(order.getId());
            listVo.setOrderNo(order.getOrderNo());
            listVo.setUserid(order.getUserid());
            listVo.setUsername(userMapper.selectNameByPrimaryKey(order.getUserid()));
            listVo.setAreaid(order.getAreaid());
            listVo.setLocation(order.getLocation());
            listVo.setStarttime(DateUtil.dateToString(order.getStarttime()));
            listVo.setPrice(order.getPrice());
            listVo.setUsetime(order.getUsetime());
            listVo.setPayment(order.getPayment());
            listVo.setPaymenttime(DateUtil.dateToString(order.getPaymenttime()));
            listVo.setPaystatus(order.getPaystatus() ==0?"未完成":"已完成");
            listVo.setCreatetime(DateUtil.dateToString(order.getCreatetime()));
            orderListVos.add(listVo);
        }
        return orderListVos;
    }
}

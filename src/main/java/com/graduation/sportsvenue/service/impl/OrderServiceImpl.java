package com.graduation.sportsvenue.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.graduation.sportsvenue.DTO.AddOrderDTO;
import com.graduation.sportsvenue.bean.Order;
import com.graduation.sportsvenue.bean.PayInfo;
import com.graduation.sportsvenue.bean.Venue;
import com.graduation.sportsvenue.common.Const;
import com.graduation.sportsvenue.common.ServiceResponse;
import com.graduation.sportsvenue.dao.OrderMapper;
import com.graduation.sportsvenue.dao.PayInfoMapper;
import com.graduation.sportsvenue.dao.UserMapper;
import com.graduation.sportsvenue.dao.VenueMapper;
import com.graduation.sportsvenue.service.OrderService;
import com.graduation.sportsvenue.util.BigDecimalUtils;
import com.graduation.sportsvenue.util.DateUtil;
import com.graduation.sportsvenue.vo.OrderListVo;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
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

    @Autowired
    PayInfoMapper payInfoMapper;
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
     * @param addOrderDTO
     * @return
     */
    @Override
    public ServiceResponse createOrder(Integer userId, AddOrderDTO addOrderDTO) {
        JSONObject jsonObject = JSONObject.fromObject(addOrderDTO);
        Integer venueId = Integer.valueOf(String.valueOf(jsonObject.get("venueId")));
        String startTime = null;
        String endTime = null;
        try {
            startTime = DateUtil.timeTchange(String.valueOf(jsonObject.get("startTime")));
            endTime = DateUtil.timeTchange(String.valueOf(jsonObject.get("endTime")));

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (userId == null || venueId == null || StringUtils.isBlank(endTime) || StringUtils.isBlank(startTime)) {
            return ServiceResponse.createErrorResponse("参数错误");
        }
        Venue venue = venueMapper.selectByVenueId(venueId);
        if (venue == null) {
            return ServiceResponse.createErrorResponse("当前场地已被占用或已删除");
        }
        //计算总价格
        Integer useTime = DateUtil.getHour(endTime, startTime);
        BigDecimal payment = BigDecimalUtils.mul(venue.getPrice().doubleValue(), useTime);
        //生成订单号
        Long orderNo = System.currentTimeMillis() + new Random().nextInt(100);
        //生成订单
        Order order = createOrder(userId, venue, payment, orderNo, useTime, startTime, endTime);
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
     * 删除场地
     * @param orderId
     * @return
     */
    @Override
    public ServiceResponse deleteOrder(String orderId) {
        JSONObject jsonObject = JSONObject.fromObject(orderId);
        Integer orderid = (Integer) jsonObject.get("orderId");
        if (orderid != null){
            int deleteResult = orderMapper.deleteByPrimaryKey(orderid);
            if (deleteResult > 0){
                return ServiceResponse.createSuccessResponse("删除成功");
            }
        }
        return ServiceResponse.createErrorResponse("参数错误，删除失败");
    }

    /**
     * 支付订单
     * @param orderId
     * @return
     */
    @Override
    public ServiceResponse payOrder(Integer userId,String orderId) {
        JSONObject jsonObject = JSONObject.fromObject(orderId);
        Integer orderid = (Integer) jsonObject.get("orderId");
        if (orderid != null){
            Order order = orderMapper.selectByPrimaryKey(orderid);
            if (order.getPaystatus()== 1){
                return ServiceResponse.createErrorResponse("订单已支付");
            }else{
                int payResult = orderMapper.payOrder(orderid);
                if (payResult > 0){
                    Order order1 = orderMapper.selectByPrimaryKey(orderid);
                    PayInfo payInfo = getPayInfo(userId,order1);
                    System.out.println("payInfo = " + payInfo);
                    int insertPayInfo = payInfoMapper.insert(payInfo);
                    if (insertPayInfo > 0){
                        return ServiceResponse.createSuccessResponse("支付完成");
                    }
                    return ServiceResponse.createErrorResponse("完成支付，但存入支付信息失败");
                }
            }
        }
        return ServiceResponse.createErrorResponse("支付失败");
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
    private Order createOrder(Integer userId, Venue venue, BigDecimal payment, Long orderNo, Integer useTime, String startTime, String endTime) {
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
        order.setEndtime(DateUtil.stringToDate(endTime));
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
            listVo.setEndtime(DateUtil.dateToString(order.getEndtime()));
            listVo.setPrice(order.getPrice());
            listVo.setUsetime(order.getUsetime());
            listVo.setPayment(order.getPayment());
            listVo.setPaymenttime(DateUtil.dateToString(order.getPaymenttime()));
            listVo.setPaystatus(order.getPaystatus() == 0 ? "未完成" : "已完成");
            listVo.setCreatetime(DateUtil.dateToString(order.getCreatetime()));
            orderListVos.add(listVo);
        }
        return orderListVos;
    }

    /**
     * 创建支付表对象
     * @param order
     * @return
     */
    private PayInfo getPayInfo(Integer userId,Order order){
        PayInfo payInfo = new PayInfo();
        String platformNum = String.valueOf(System.currentTimeMillis())+order.getOrderNo() ;
        payInfo.setOrderNo(order.getOrderNo());
        payInfo.setPlatformNum(platformNum);
        payInfo.setCreatetime(order.getCreatetime());
        payInfo.setUserid(userId);
        payInfo.setStatus(order.getPaystatus());
        return payInfo;
    }
}

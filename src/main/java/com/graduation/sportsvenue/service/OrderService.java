package com.graduation.sportsvenue.service;

import com.graduation.sportsvenue.DTO.AddOrderDTO;
import com.graduation.sportsvenue.common.ServiceResponse;

import java.util.Date;

public interface OrderService {
    /**
     * 管理员查看所有订单
     * @return
     */
    ServiceResponse selectOrderList(Integer pageNum,Integer pageSize);

    /**
     * 管理员查看已完成
     * @return
     */
    ServiceResponse selectOrderPay(Integer pageNum,Integer pageSize);

    /**
     * 用户查看自己的订单
     * @param userId
     * @return
     */
    ServiceResponse userGetOrder(Integer userId,Integer pageNum,Integer pageSize);

    /**
     * 预定场地
     * @param userId
     * @param addOrderDTO
     * @return
     */
    ServiceResponse createOrder(Integer userId, AddOrderDTO addOrderDTO);


}

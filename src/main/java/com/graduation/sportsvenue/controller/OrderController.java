package com.graduation.sportsvenue.controller;

import com.graduation.sportsvenue.bean.User;
import com.graduation.sportsvenue.common.Const;
import com.graduation.sportsvenue.common.ServiceResponse;
import com.graduation.sportsvenue.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;

@RestController
@RequestMapping(value = "order")
public class OrderController {
    @Autowired
    OrderService orderService;

    /**
     * 管理员查看所有订单
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "selectOrder")
    public ServiceResponse selectOrder(@RequestParam(required = false,defaultValue = "1")Integer pageNum,
                                       @RequestParam(required = false,defaultValue = "10")Integer pageSize){

        return orderService.selectOrderList(pageNum,pageSize);
    }

    /**
     * 管理员查看已完成订单
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "selectOrderPay")
    public ServiceResponse selectOrderPay(@RequestParam(required = false,defaultValue = "1")Integer pageNum,
                                          @RequestParam(required = false,defaultValue = "10")Integer pageSize){

        return orderService.selectOrderPay(pageNum,pageSize);
    }

    /**
     * 用户查看订单
     * @param session
     * @return
     */
    @RequestMapping(value = "userGetOrder")
    public ServiceResponse userGetOrder(HttpSession session,
                                        @RequestParam(required = false,defaultValue = "1")Integer pageNum,
                                        @RequestParam(required = false,defaultValue = "10")Integer pageSize){
        User user = (User) session.getAttribute(Const.CURRENTUSER);
        if (user == null) {
            return ServiceResponse.createErrorResponse("需要登录");
        }
        return orderService.userGetOrder(user.getId(),pageNum,pageSize);
    }

    /**
     * 用户预定场地
     * @param session
     * @param venueId
     * @param useTime
     * @return
     */
    @RequestMapping(value = "addOrder")
    public ServiceResponse userAddOrder(HttpSession session , Integer venueId, Integer useTime , String startTime){
        User user = (User) session.getAttribute(Const.CURRENTUSER);
        if (user == null) {
            return ServiceResponse.createErrorResponse("需要登录");
        }
        return orderService.createOrder(user.getId(),venueId,useTime ,startTime);
    }
}

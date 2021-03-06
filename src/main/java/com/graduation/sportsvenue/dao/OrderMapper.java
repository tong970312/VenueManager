package com.graduation.sportsvenue.dao;

import com.graduation.sportsvenue.bean.Order;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface OrderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_v
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_v
     *
     * @mbggenerated
     */
    int insert(Order record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_v
     *
     * @mbggenerated
     */
    Order selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_v
     *
     * @mbggenerated
     */
    List<Order> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_v
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Order record);


    List<Order> selectPayOrder();

    List<Order> selectOrderByUserId(Integer userId);

    List<Order> selectByAreaId(Integer areaId);

    int payOrder(Integer orderId);

}
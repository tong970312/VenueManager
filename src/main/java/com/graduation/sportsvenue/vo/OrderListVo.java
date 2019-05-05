package com.graduation.sportsvenue.vo;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class OrderListVo {

    private Integer id;
    private Long orderNo;
    private Integer userid;
    private String username;
    private Integer areaid;
    private String starttime;
    private String location;
    private BigDecimal price;
    private Integer usetime;
    private BigDecimal payment;
    private String paymenttime;
    private String paystatus;
    private String createtime;
}

package com.graduation.sportsvenue.bean;

import lombok.Data;

import java.util.Date;
@Data
public class PayInfo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payinfo.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payinfo.order_no
     *
     * @mbggenerated
     */
    private Long orderNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payinfo.userid
     *
     * @mbggenerated
     */
    private Integer userid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payinfo.platform_num
     *
     * @mbggenerated
     */
    private String platformNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payinfo.status
     *
     * @mbggenerated
     */
    private Integer status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payinfo.createTime
     *
     * @mbggenerated
     */
    private Date createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payinfo.updateTime
     *
     * @mbggenerated
     */
    private Date updatetime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column payinfo.id
     *
     * @return the value of payinfo.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column payinfo.id
     *
     * @param id the value for payinfo.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column payinfo.order_no
     *
     * @return the value of payinfo.order_no
     *
     * @mbggenerated
     */
    public Long getOrderNo() {
        return orderNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column payinfo.order_no
     *
     * @param orderNo the value for payinfo.order_no
     *
     * @mbggenerated
     */
    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column payinfo.userid
     *
     * @return the value of payinfo.userid
     *
     * @mbggenerated
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column payinfo.userid
     *
     * @param userid the value for payinfo.userid
     *
     * @mbggenerated
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column payinfo.platform_num
     *
     * @return the value of payinfo.platform_num
     *
     * @mbggenerated
     */
    public String getPlatformNum() {
        return platformNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column payinfo.platform_num
     *
     * @param platformNum the value for payinfo.platform_num
     *
     * @mbggenerated
     */
    public void setPlatformNum(String platformNum) {
        this.platformNum = platformNum == null ? null : platformNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column payinfo.status
     *
     * @return the value of payinfo.status
     *
     * @mbggenerated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column payinfo.status
     *
     * @param status the value for payinfo.status
     *
     * @mbggenerated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column payinfo.createTime
     *
     * @return the value of payinfo.createTime
     *
     * @mbggenerated
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column payinfo.createTime
     *
     * @param createtime the value for payinfo.createTime
     *
     * @mbggenerated
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column payinfo.updateTime
     *
     * @return the value of payinfo.updateTime
     *
     * @mbggenerated
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column payinfo.updateTime
     *
     * @param updatetime the value for payinfo.updateTime
     *
     * @mbggenerated
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

}
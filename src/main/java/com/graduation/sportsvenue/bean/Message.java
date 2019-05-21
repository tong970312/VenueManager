package com.graduation.sportsvenue.bean;

import lombok.Data;

import java.util.Date;
@Data
public class Message {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column message.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column message.noticemsg
     *
     * @mbggenerated
     */
    private String noticemsg;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column message.adminname
     *
     * @mbggenerated
     */
    private String adminname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column message.createTime
     *
     * @mbggenerated
     */
    private Date createtime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column message.id
     *
     * @return the value of message.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column message.id
     *
     * @param id the value for message.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column message.noticemsg
     *
     * @return the value of message.noticemsg
     *
     * @mbggenerated
     */
    public String getNoticemsg() {
        return noticemsg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column message.noticemsg
     *
     * @param noticemsg the value for message.noticemsg
     *
     * @mbggenerated
     */
    public void setNoticemsg(String noticemsg) {
        this.noticemsg = noticemsg == null ? null : noticemsg.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column message.adminname
     *
     * @return the value of message.adminname
     *
     * @mbggenerated
     */
    public String getAdminname() {
        return adminname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column message.adminname
     *
     * @param adminname the value for message.adminname
     *
     * @mbggenerated
     */
    public void setAdminname(String adminname) {
        this.adminname = adminname == null ? null : adminname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column message.createTime
     *
     * @return the value of message.createTime
     *
     * @mbggenerated
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column message.createTime
     *
     * @param createtime the value for message.createTime
     *
     * @mbggenerated
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }


    public Message(String noticemsg, String adminname) {
        this.noticemsg = noticemsg;
        this.adminname = adminname;
    }
}
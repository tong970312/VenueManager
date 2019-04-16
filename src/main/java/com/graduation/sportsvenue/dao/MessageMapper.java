package com.graduation.sportsvenue.dao;

import com.graduation.sportsvenue.bean.Message;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface MessageMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table message
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table message
     *
     * @mbggenerated
     */
    int insert(Message record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table message
     *
     * @mbggenerated
     */
    Message selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table message
     *
     * @mbggenerated
     */
    List<Message> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table message
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Message record);
}
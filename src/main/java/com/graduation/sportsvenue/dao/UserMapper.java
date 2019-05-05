package com.graduation.sportsvenue.dao;

import com.graduation.sportsvenue.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface UserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account
     *
     * @mbggenerated
     */
    int insert(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account
     *
     * @mbggenerated
     */
    User selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account
     *
     * @mbggenerated
     */
    List<User> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(User record);

    /**
     * 账户登录
     * @param username
     * @param password
     * @return
     */
    User selectByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    /**
     * 检查是否重名
     * @param username
     * @return User
     */
    User selectByName(String username);

    /**
     * 根据id修改字段
     * @param id
     * @return
     */
    int removeById(Integer id);

    /**
     * 更新账户信息
     * @param user
     * @return
     */
    int update(User user);

    String selectNameByPrimaryKey(Integer userId);
}
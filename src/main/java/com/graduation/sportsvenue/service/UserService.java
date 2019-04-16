package com.graduation.sportsvenue.service;

import com.graduation.sportsvenue.bean.User;
import com.graduation.sportsvenue.common.ServiceResponse;

import java.util.List;

/**
 * 账户模块接口
 */
public interface UserService {
    /**
     * 登录接口
     *
     * @param username
     * @param password
     * @return 账户对象信息
     */
    ServiceResponse login(String username, String password);

    /**
     * 账户注册
     *
     * @param user
     * @return
     */
    ServiceResponse register(User user);

    /**
     * 根据id修改对应用户的ifdelete
     * @param id
     * @return integer
     */
    ServiceResponse remove(Integer id);

    /**
     * 账户更新
     * @param user
     * @return
     */
    ServiceResponse update(User user);
}

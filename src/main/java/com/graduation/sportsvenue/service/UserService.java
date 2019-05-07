package com.graduation.sportsvenue.service;

import com.graduation.sportsvenue.DTO.RegisterDTO;
import com.graduation.sportsvenue.DTO.UserDTO;
import com.graduation.sportsvenue.bean.User;
import com.graduation.sportsvenue.common.ServiceResponse;

/**
 * 账户模块接口
 */
public interface UserService {
    /**
     * 登录接口
     *
     * @param userDTO
     * @return 账户对象信息
     */
    ServiceResponse login(UserDTO userDTO);

    /**
     * 账户注册
     *
     * @param registerDTO
     * @return
     */
    ServiceResponse register(String registerDTO);

    /**
     * 根据id修改对应用户的ifdelete
     * @param id
     * @return integer
     */
    ServiceResponse remove(Integer id);

    /**
     * 账户更新
     * @param jsonStr
     * @return
     */
    ServiceResponse update(String jsonStr);

    ServiceResponse getUserList(Integer pageNum,Integer pageSize);
}

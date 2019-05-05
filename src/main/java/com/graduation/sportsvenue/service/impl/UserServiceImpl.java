package com.graduation.sportsvenue.service.impl;

import com.graduation.sportsvenue.bean.User;
import com.graduation.sportsvenue.common.Const;
import com.graduation.sportsvenue.common.ServiceResponse;
import com.graduation.sportsvenue.dao.UserMapper;
import com.graduation.sportsvenue.service.UserService;
import com.graduation.sportsvenue.util.DateUtil;
import com.graduation.sportsvenue.vo.UserUpdateBean;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 账户模块实现类
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    /**
     * 账户登录
     *
     * @param username
     * @param password
     * @return 用户信息
     */
    @Override
    public ServiceResponse login(String username, String password) {
        if (StringUtils.isBlank(username)) {
            return ServiceResponse.createErrorResponse("用户名不能为空");
        }
        if (StringUtils.isBlank(password)) {
            return ServiceResponse.createErrorResponse("密码不能为空");
        }
        //查找账户是否存在
        User user = userMapper.selectByUsernameAndPassword(username, password);
        if (user == null) {
            return ServiceResponse.createErrorResponse("账户不存在");
        }
        return ServiceResponse.createSuccessResponse("登录成功", user);
    }

    /**
     * 账户注册
     *
     * @param user
     * @return
     */
    @Override
    public ServiceResponse register(User user) {
        if (user == null) {
            return ServiceResponse.createErrorResponse("参数错误、注册失败");
        }

        ServiceResponse serviceResponse = checkName(user.getUsername());
        if (serviceResponse.getStatus() == Const.ACCOUNT_EXIT) {
            return ServiceResponse.createErrorResponse("用户名存在");
        }

        if (serviceResponse.getStatus() == Const.ACCOUNT_NOT_EXIT) {
            int registerResult = userMapper.insert(user);
            if (registerResult > 0) {
                return ServiceResponse.createSuccessResponse("注册成功");
            }
        }
        return ServiceResponse.createErrorResponse("注册失败");
    }

    /**
     * 根据id更新ifdelete字段 管理员操作
     *
     * @param id
     * @return
     */
    @Override
    public ServiceResponse remove(Integer id) {
        if (id == null) {
            return ServiceResponse.createErrorResponse("参数为空");
        }
        User user = userMapper.selectByPrimaryKey(id);

        if (user == null) {
            return ServiceResponse.createErrorResponse("账户不存在");
        }
        if (user.getIfdelete() == Const.ACCOUNT_DELETE) {
            return ServiceResponse.createErrorResponse("账户已删除");
        }
        int removeResult = userMapper.removeById(id);
        if (removeResult > 0) {
            return ServiceResponse.createSuccessResponse("删除成功");
        }
        return ServiceResponse.createErrorResponse("删除失败");
    }

    /**
     * 账户更新
     *
     * @param user
     * @return
     */
    @Override
    public ServiceResponse update(User user) {
        if (user==null){
            return  ServiceResponse.createErrorResponse("参数不能为空");
        }
        int updateResult = userMapper.update(user);
        if (updateResult>0){
            User updateUser = userMapper.selectByPrimaryKey(user.getId());
            UserUpdateBean updateBean = new UserUpdateBean();
            updateBean.setId(updateUser.getId());
            updateBean.setUsername(updateUser.getUsername());
            updateBean.setPassword(updateUser.getPassword());
            updateBean.setPhone(updateUser.getPhone());
            updateBean.setRole(updateUser.getRole());
            updateBean.setIfdelete(updateUser.getIfdelete());
            updateBean.setUpdatetime(DateUtil.dateToString(updateUser.getUpdatetime()));
            return ServiceResponse.createSuccessResponse("更新成功",updateBean);
        }

        return ServiceResponse.createErrorResponse("更新失败");
    }

    /**
     * 用来检查是否有重名
     *
     * @param username
     * @return
     */
    public ServiceResponse checkName(String username) {
        if (StringUtils.isBlank(username)) {
            return ServiceResponse.createErrorResponse("参数不能为空");
        }
        User user = userMapper.selectByName(username);
        if (user == null) {
            return ServiceResponse.createSuccessResponse(Const.ACCOUNT_NOT_EXIT);
        }
        return ServiceResponse.createErrorResponse(Const.ACCOUNT_EXIT);
    }
}

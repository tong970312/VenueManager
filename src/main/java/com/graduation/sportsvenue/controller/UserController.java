package com.graduation.sportsvenue.controller;

import com.graduation.sportsvenue.bean.User;
import com.graduation.sportsvenue.common.Const;
import com.graduation.sportsvenue.common.ServiceResponse;
import com.graduation.sportsvenue.service.UserService;
import com.graduation.sportsvenue.util.DateUtil;
import com.graduation.sportsvenue.vo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 账户操作
 * 登录，注册、修改,退出
 * 管理员可以删除
 */
@RequestMapping(value = "account")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 账户登录
     *
     * @param session
     * @param username
     * @param password
     * @return serviceResponse
     */
    @RequestMapping(value = "login")
    public ServiceResponse login(HttpSession session, String username, String password) {
        ServiceResponse serviceResponse = userService.login(username, password);
        if (serviceResponse.isSuccess()) {
            User user = (User) serviceResponse.getData();
            if (user.getRole() == Const.USERROLE) {
                session.setAttribute(Const.CURRENTUSER, user);
            }
            if (user.getRole() == Const.ADMINROLE) {
                session.setAttribute(Const.CURRENTADMIN, user);
            }
        }
        return serviceResponse;
    }

    /**
     * 账户注册
     *
     * @param user
     * @return 注册成功
     */
    @RequestMapping(value = "register")
    public ServiceResponse register(User user) {

        return userService.register(user);
    }

    /**
     * 管理员删除账户
     *
     * @param id
     * @return 删除成功
     */
    @RequestMapping(value = "remove")
    public ServiceResponse remove(HttpSession session, Integer id) {
        User user = (User) session.getAttribute(Const.CURRENTADMIN);
        if (user == null) {
            return ServiceResponse.createErrorResponse("需要管理员登录");
        }
        return userService.remove(id);
    }

    /**
     * 获取信息
     *
     * @param session
     * @return userInfo
     */
    @RequestMapping(value = "getSelfInfo")
    public ServiceResponse getSelfInfo(HttpSession session) {
        Object obj = session.getAttribute(Const.CURRENTUSER);
        if (obj != null && obj instanceof User) {
            User user = (User) obj;
            UserInfoVo userInfo = new UserInfoVo();
            userInfo.setId(user.getId());
            userInfo.setUsername(user.getUsername());
            userInfo.setPassword(user.getPassword());
            userInfo.setPhone(user.getPhone());
            userInfo.setIfdelete(user.getIfdelete());
            userInfo.setRole(user.getRole());
            userInfo.setCreatetime(DateUtil.dateToString(user.getCreatetime()));
            userInfo.setUpdatetime(DateUtil.dateToString(user.getUpdatetime()));
            return ServiceResponse.createSuccessResponse("用户信息", userInfo);
        }
        return ServiceResponse.createErrorResponse("账户未登录");
    }

    /**
     * 更新操作
     *
     * @param user
     * @return userUpdateBean
     */
    @RequestMapping(value = "update")
    public ServiceResponse update(User user) {

        return userService.update(user);
    }

    /**
     * 用户退出 删除session
     *
     * @param session
     * @return 用户成功退出
     */
    @RequestMapping(value = "user_exit")
    public ServiceResponse user_exit(HttpSession session) {
        session.removeAttribute(Const.CURRENTUSER);
        return ServiceResponse.createSuccessResponse("用户成功退出");
    }

    /**
     * 管理员退出 删除session
     *
     * @param session
     * @return 管理员成功退出
     */
    @RequestMapping(value = "admin_exit")
    public ServiceResponse admin_exit(HttpSession session) {
        session.removeAttribute(Const.CURRENTADMIN);
        return ServiceResponse.createSuccessResponse("管理员成功退出");
    }

}

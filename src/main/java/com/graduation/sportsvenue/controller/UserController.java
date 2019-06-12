package com.graduation.sportsvenue.controller;

import com.graduation.sportsvenue.DTO.UserDTO;
import com.graduation.sportsvenue.bean.User;
import com.graduation.sportsvenue.common.Const;
import com.graduation.sportsvenue.common.ServiceResponse;
import com.graduation.sportsvenue.service.UserService;
import com.graduation.sportsvenue.util.DateUtil;
import com.graduation.sportsvenue.vo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

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
     * @return serviceResponse
     */
    @RequestMapping(value = "login")
    public ServiceResponse login(HttpSession session, @RequestBody UserDTO userDTO) {
        ServiceResponse serviceResponse = userService.login(userDTO);
        if (serviceResponse.isSuccess()) {
            User user = (User) serviceResponse.getData();
            if (user.getRole() == Const.USERROLE) {
                session.setAttribute(Const.CURRENTUSER, user);
            }
            else{
                session.setAttribute(Const.CURRENTADMIN, user);
            }
        }
      return serviceResponse;

    }

    /**
     * 账户注册
     *
     * @param RegisterDTO
     * @return 注册成功
     */
    @RequestMapping(value = "register")
    public ServiceResponse register(@RequestBody String RegisterDTO) {

        return userService.register(RegisterDTO);
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

    @RequestMapping(value = "getAdmin")
    public ServiceResponse getSelf(HttpSession session){
        Object obj = session.getAttribute(Const.CURRENTADMIN);
        if (obj != null && obj instanceof User ){
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
            return ServiceResponse.createSuccessResponse("管理员信息",userInfo);
        }
        return ServiceResponse.createErrorResponse("账户未登录");
    }

    /**
     * 更新操作
     *
     * @param
     * @return userUpdateBean
     */
    @RequestMapping(value = "update")
    public ServiceResponse update(@RequestBody String jsonStr) {

        return userService.update(jsonStr);
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

    /**
     * 获取用户列表
     * @return
     */
    @RequestMapping(value = "getUserList")
    public ServiceResponse getUserList(@RequestParam(required = false,defaultValue = "1")Integer pageNum,
                                       @RequestParam(required = false,defaultValue = "10")Integer pageSize){


        return userService.getUserList(pageNum,pageSize);

    }


}

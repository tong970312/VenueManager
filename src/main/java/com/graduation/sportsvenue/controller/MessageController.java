package com.graduation.sportsvenue.controller;

import com.graduation.sportsvenue.bean.User;
import com.graduation.sportsvenue.common.Const;
import com.graduation.sportsvenue.common.ServiceResponse;
import com.graduation.sportsvenue.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * 系统通知
 * 管理员发布通知、删除通知
 * 通知显示在系统公告栏处
 */
@RestController
@RequestMapping(value = "message")
public class MessageController {

    @Autowired
    MessageService messageService;

    /**
     * 管理员发布通知
     *
     * @param session
     * @param notice
     * @return
     */
    @RequestMapping(value = "send_notice")
    public ServiceResponse sendNotice(HttpSession session, String notice) {
        User user = (User) session.getAttribute(Const.CURRENTADMIN);
        if (user == null) {
            return ServiceResponse.createErrorResponse("管理员未登录");
        }
        return messageService.sendNotice(user.getUsername(), notice);
    }

    /**
     * 显示所有通知
     *
     * @return
     */
    @RequestMapping(value = "getAllMsg")
    public ServiceResponse getAllMsg() {

        return messageService.getAllMsg();
    }
}

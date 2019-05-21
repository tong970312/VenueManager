package com.graduation.sportsvenue.service;

import com.graduation.sportsvenue.common.ServiceResponse;

/**
 * 系统通知相关接口
 */
public interface MessageService {
    /**
     * 发布通知接口
     * @param notice
     * @return
     */
    ServiceResponse sendNotice(String adminName,String notice);

    /**
     * 删除通知
     * @param messageId
     * @return
     */
    ServiceResponse deleteMsg(String messageId);

    /**
     * 显示所有通知
     * @return
     */
    ServiceResponse getAllMsg();

}

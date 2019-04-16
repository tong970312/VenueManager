package com.graduation.sportsvenue.service.impl;

import com.graduation.sportsvenue.bean.Message;
import com.graduation.sportsvenue.common.ServiceResponse;
import com.graduation.sportsvenue.dao.MessageMapper;
import com.graduation.sportsvenue.service.MessageService;
import com.graduation.sportsvenue.util.DateUtil;
import com.graduation.sportsvenue.vo.MessageVo;
import org.apache.commons.lang.StringUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统通知接口实现类
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageMapper messageMapper;

    /**
     * 发布系统通知
     *
     * @param notice
     * @return
     */
    @Override
    public ServiceResponse sendNotice(String adminName, String notice) {
        if (StringUtils.isBlank(notice)) {
            return ServiceResponse.createErrorResponse("不能发布空消息");
        }
        Message message = new Message(adminName, notice);
        int sendResult = messageMapper.insert(message);
        if (sendResult > 0) {
            return ServiceResponse.createSuccessResponse("发布成功");
        }

        return ServiceResponse.createErrorResponse("发布失败");
    }

    /**
     * 显示所有通知
     *
     * @return List<MessageVo>
     */
    @Override
    public ServiceResponse getAllMsg() {
        List<Message> messageList = messageMapper.selectAll();
        List<MessageVo> messageVos = new ArrayList<>();

        if (messageList != null && messageList.size() != 0) {
            for (Message msg : messageList) {
                MessageVo msgvo = new MessageVo();
                msgvo.setId(msg.getId());
                msgvo.setAdminname(msg.getAdminname());
                msgvo.setNoticemsg(msg.getNoticemsg());
                msgvo.setCreatetime(DateUtil.dateToString(msg.getCreatetime()));
                messageVos.add(msgvo);
            }
            return ServiceResponse.createSuccessResponse("所有的通知", messageVos);
        }
        return ServiceResponse.createErrorResponse("管理员未发布场馆通知");
    }
}

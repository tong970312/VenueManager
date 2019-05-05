package com.graduation.sportsvenue.controller;

import com.graduation.sportsvenue.bean.User;
import com.graduation.sportsvenue.bean.Venue;
import com.graduation.sportsvenue.common.Const;
import com.graduation.sportsvenue.common.ServiceResponse;
import com.graduation.sportsvenue.service.VenueService;
import com.graduation.sportsvenue.util.JsonFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

/**
 * 场地管理
 * 场地的增删改查
 */
@RestController
@RequestMapping(value = "Venue")
public class VenueController {

    @Autowired
    VenueService venueService;

    /**
     * 添加场地
     * @param session
     * @param jsonStr
     * @param file
     * @return
     */
    @RequestMapping(value = "addVenue",method =RequestMethod.POST )
    public ServiceResponse addVenue(HttpSession session, String jsonStr, MultipartFile file) {
//        User user = (User) session.getAttribute(Const.CURRENTADMIN);
//        if (user==null){
//            return ServiceResponse.createErrorResponse("请管理员登录");
//        }
        Venue venue = null;
        try {
            venue = JsonFormatUtils.transformJsonToObject(Venue.class,jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return venueService.addVenue(venue,file);
    }

    /**
     * 场地删除
     * @param session
     * @param venueId
     * @return
     */
    @RequestMapping(value = "deleteVenue")
    public ServiceResponse delete(HttpSession session,Integer venueId){
        User user = (User) session.getAttribute(Const.CURRENTADMIN);
        if (user==null){
            return ServiceResponse.createErrorResponse("请管理员登录");
        }
        return venueService.delete(venueId);
    }

}

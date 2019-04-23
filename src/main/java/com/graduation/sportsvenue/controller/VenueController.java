package com.graduation.sportsvenue.controller;

import com.graduation.sportsvenue.bean.User;
import com.graduation.sportsvenue.bean.Venue;
import com.graduation.sportsvenue.common.Const;
import com.graduation.sportsvenue.common.ServiceResponse;
import com.graduation.sportsvenue.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "addVenue")
    public ServiceResponse addVenue(HttpSession session, Venue venue) {
        User user = (User) session.getAttribute(Const.CURRENTADMIN);
        if (user==null){
            return ServiceResponse.createErrorResponse("请管理员登录");
        }
        return venueService.addVenue(venue);
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

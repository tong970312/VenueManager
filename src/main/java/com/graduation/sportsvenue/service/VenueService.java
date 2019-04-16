package com.graduation.sportsvenue.service;

import com.graduation.sportsvenue.bean.Venue;
import com.graduation.sportsvenue.common.ServiceResponse;

/**
 * 场地管理接口
 */
public interface VenueService {
    /**
     * 上传场地图片(未上传图片)
     * @param venue
     * @return
     */
    ServiceResponse addVenue(Venue venue);
}

package com.graduation.sportsvenue.service;

import com.graduation.sportsvenue.bean.Venue;
import com.graduation.sportsvenue.common.ServiceResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * 场地管理接口
 */
public interface VenueService {
    /**
     * 上传场地图片(未上传图片)
     * @param venue
     * @return
     */
    ServiceResponse addVenue(Venue venue , MultipartFile multipartFile);

    /**
     * 删除场地
     * @param venueId
     * @return
     */
    ServiceResponse delete(Integer venueId);

    /**
     * 获取场地列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    ServiceResponse getVenueList(Integer pageNum,Integer pageSize);
}

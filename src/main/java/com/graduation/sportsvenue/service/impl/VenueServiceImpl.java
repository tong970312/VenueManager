package com.graduation.sportsvenue.service.impl;

import com.graduation.sportsvenue.bean.Venue;
import com.graduation.sportsvenue.common.ServiceResponse;
import com.graduation.sportsvenue.dao.VenueMapper;
import com.graduation.sportsvenue.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 场地管理实现类
 */
@Service
public class VenueServiceImpl implements VenueService {

    @Autowired
    VenueMapper venueMapper;

    /**
     * 添加场地
     * @param venue
     * @return
     */
    @Override
    public ServiceResponse addVenue(Venue venue) {
        return null;
    }
}

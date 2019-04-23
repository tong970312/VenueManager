package com.graduation.sportsvenue.dao;

import com.graduation.sportsvenue.bean.Venue;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface VenueMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table venue
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table venue
     *
     * @mbggenerated
     */
    int insert(Venue record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table venue
     *
     * @mbggenerated
     */
    Venue selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table venue
     *
     * @mbggenerated
     */
    List<Venue> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table venue
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Venue record);

    int deleteByVenueId(Integer id);
}
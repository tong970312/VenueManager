package com.graduation.sportsvenue.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.graduation.sportsvenue.bean.Order;
import com.graduation.sportsvenue.bean.Venue;
import com.graduation.sportsvenue.common.Const;
import com.graduation.sportsvenue.common.ServiceResponse;
import com.graduation.sportsvenue.dao.OrderMapper;
import com.graduation.sportsvenue.dao.VenueMapper;
import com.graduation.sportsvenue.service.VenueService;
import com.graduation.sportsvenue.util.DateUtil;
import com.graduation.sportsvenue.util.FTPUtils;
import com.graduation.sportsvenue.vo.VenueListVo;
import org.apache.commons.lang.StringUtils;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.hibernate.validator.internal.util.CollectionHelper.newHashMap;

/**
 * 场地管理实现类
 */
@Service
public class VenueServiceImpl implements VenueService {

    @Autowired
    VenueMapper venueMapper;
    @Autowired
    OrderMapper orderMapper;

    /**
     * 添加场地
     *
     * @param venue
     * @return
     */
    @Override
    public ServiceResponse addVenue(Venue venue, MultipartFile file) {
        if (venue == null) {
            return ServiceResponse.createErrorResponse("场地参数错误");
        }
        if (file == null) {
            return ServiceResponse.createErrorResponse("文件错误");
        }

        Map<String, String> fileMap = uploadFile(file);

        if (fileMap == null) {
            return ServiceResponse.createErrorResponse("图片上传失败");
        }
        venue.setImage(fileMap.get("URI"));
        venue.setStatus(0);
        venue.setIfdelete(0);

        int insertResult = venueMapper.insert(venue);
        if (insertResult > 0) {
            return ServiceResponse.createSuccessResponse("添加场地成功");
        }

        return ServiceResponse.createSuccessResponse("添加失败");
    }

    /**
     * 上传图片到FTP
     *
     * @param file
     * @return
     */
    public static Map<String, String> uploadFile(MultipartFile file) {
        //  String path = "D:\\ftppicture";
        String path = "D:\\webstormspace\\demo\\src\\picture";
        //获取图片名称
        String originalFileName = file.getOriginalFilename();
        //获取扩展名
        String extensionName = originalFileName.substring(originalFileName.lastIndexOf("."));
        //生成唯一名字
        String newName = UUID.randomUUID().toString() + extensionName;
        File pathfile = new File(path);
        //如果不存在
        if (!pathfile.exists()) {
            //设置可写
            pathfile.setWritable(true);
            pathfile.mkdir();
        }
        File file1 = new File(path, newName);
        try {
            file.transferTo(file1);
            //长传到图片服务器
           // System.out.println("开始上传图片到服务器");
            //  FTPUtils.uploadFile(file1);
            //本地删除应用服务器上的图片
            // file1.delete();
            Map<String, String> map = newHashMap();
            map.put("URI", newName);
            map.put("URL", Const.IMAGEHOST + "/" + newName);
            return map;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除场地
     *
     * @param venueId
     * @return
     */
    @Override
    public ServiceResponse delete(Integer venueId) {
        if (venueId == null) {
            return ServiceResponse.createErrorResponse("参数错误");
        }
        int deleteResult = venueMapper.deleteByVenueId(venueId);
        if (deleteResult > 0) {
            return ServiceResponse.createSuccessResponse("删除成功");
        }
        return ServiceResponse.createErrorResponse("删除失败");
    }

    /**
     * 获取场地
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public ServiceResponse getVenueList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Venue> venueList = venueMapper.selectCanReserve();
        List<VenueListVo> venueListVos = Lists.newArrayList();
        if (venueList != null && venueList.size() > 0) {
            for (Venue venue : venueList) {
                VenueListVo vo = new VenueListVo();
                Order order = getEndTimeByAreaId(venue.getId());
                if (order == null){
                    vo.setEndtime(null);
                    vo.setStatus("可预订");
                }else{
                    try {
                        vo.setEndtime(DateUtil.dateTochange(DateUtil.dateToString(order.getEndtime())));
                        vo.setStarttime(DateUtil.dateTochange(DateUtil.dateToString(order.getStarttime())));
                        if(order.getPaystatus() == 1){
                            vo.setStatus("已被占用");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                vo.setId(venue.getId());
                vo.setAreaname(venue.getAreaname());
                vo.setDetail(venue.getDetail());
                vo.setLocation(venue.getLocation());
                vo.setImage(Const.IMAGEURL + venue.getImage());
                vo.setPrice(venue.getPrice());
                venueListVos.add(vo);
            }
            PageInfo pageInfo = new PageInfo(venueListVos);
            return ServiceResponse.createSuccessResponse("场地列表", pageInfo);
        }

        return ServiceResponse.createErrorResponse("查找失败");
    }

    /**
     * 获取已经支付的场地使用结束时间
     * @param areaId
     * @return
     */
    private Order getEndTimeByAreaId(Integer areaId) {
        List<Order> orderList = orderMapper.selectByAreaId(areaId);
        Order order = null;
        if (orderList != null && orderList.size() > 0){
            order = orderList.get(0);
        }
        else {
            return null;
        }
        System.out.println("order = " + order);
        return order;
    }


}

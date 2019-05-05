package com.graduation.sportsvenue.service.impl;

import com.graduation.sportsvenue.bean.Venue;
import com.graduation.sportsvenue.common.Const;
import com.graduation.sportsvenue.common.ServiceResponse;
import com.graduation.sportsvenue.dao.VenueMapper;
import com.graduation.sportsvenue.service.VenueService;
import com.graduation.sportsvenue.util.FTPUtils;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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
        System.out.println("fileMap = " + fileMap);
        if (fileMap == null) {
            return ServiceResponse.createErrorResponse("图片上传失败");
        }
        venue.setImage(fileMap.get("URI"));
        venue.setStatus(0);
        venue.setIfdelete(0);
        System.out.println("venue = " + venue);

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
        String path = "D:\\ftppicture";
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
            System.out.println("开始上传图片到服务器");
            FTPUtils.uploadFile(file1);
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


}

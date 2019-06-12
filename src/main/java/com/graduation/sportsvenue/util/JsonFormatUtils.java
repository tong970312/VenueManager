package com.graduation.sportsvenue.util;

import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

/**
 * @author: litong
 * @date: 2019/4/23
 * 配置解析类
 **/
public class JsonFormatUtils {

    public static <T> T transformJsonToObject(Class<T> objectClass, String str) throws Exception {
        Object object = null;

        if (StringUtils.isNotBlank(str)) {
            net.sf.json.JSONObject jsonObject = JSONObject.fromObject(str);
            object = JSONObject.toBean(jsonObject, objectClass);
        }
        return (T)object;
    }

}

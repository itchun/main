package com.base.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

public class CommonUtil {

    //fastjson特性
    public static final SerializerFeature[] FEATURE = {SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteNullNumberAsZero};

    //status:0代表没有错误；1代表有错误
    public static String format(Integer status, Object data) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("status", status);
        map.put("data", data);
        return JSON.toJSONString(map, FEATURE);
    }

    //获取客户端ip
    public static String clienIP(HttpServletRequest request) {
        if (request == null) return null;
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.trim().isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.trim().isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.trim().isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        if (StringUtils.isNotEmpty(ip) && ip.contains(",")) {
            String[] ips = ip.split(",");
            if (ips != null && ips.length > 0) {
                ip = ips[0];
            }
        }
        return ip;
    }
}

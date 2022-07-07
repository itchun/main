package com.base.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("demo")
public class DemoController {

    @RequestMapping("request")
    public String request(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();

        // url
        String getContextPath = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + getContextPath + "/";
        String getRemoteAddress = request.getRemoteAddr();
        String getServletPath = request.getServletPath();
        String getServletContext_getRealPath = request.getServletContext().getRealPath("/");
        String getRequestURL = request.getRequestURL().toString();
        String getRequestURI = request.getRequestURI();
        String getQueryString = request.getQueryString();
        String getRemoteUser = request.getRemoteUser();
        map.put("getContextPath", getContextPath);
        map.put("basePath", basePath);
        map.put("getRemoteAddress", getRemoteAddress);
        map.put("getServletPath", getServletPath);
        map.put("getServletContext_getRealPath", getServletContext_getRealPath);
        map.put("getRequestURL", getRequestURL);
        map.put("getRequestURI", getRequestURI);
        map.put("getQueryString", getQueryString);
        map.put("getRemoteUser", getRemoteUser);

        // head
        Enumeration<String> headNames = request.getHeaderNames();
        while (headNames.hasMoreElements()) {
            String headName = headNames.nextElement();
            String value = request.getHeader(headName);
            map.put("head_" + headName, value);
        }

        //
//        request.get
        System.out.println(JSON.toJSON(map));

        return "ok";
    }

}

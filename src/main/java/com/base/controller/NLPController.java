package com.base.controller;

import com.base.util.CommonUtil;
import com.base.自然语言处理类.NLPUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

// 自然语言处理公开接口
@RestController
@RequestMapping("nlp/en")
public class NLPController {

    //全局日志记录器
    public static final Logger LOGGER = LogManager.getLogger();

    @RequestMapping("ner")
    public String ner(String text, HttpServletRequest request) throws Exception {
        if (StringUtils.isEmpty(text))
            return CommonUtil.format(0, "输入内容为空");
        if (text.length() > 999999999)
            return CommonUtil.format(0, "输入内容长度最大为 999999999");
        HashMap<String, Object> map = NLPUtil.getNER(text);
        map.put("info", "本接口支持语言：【英文】，可以抽取的实体类型为: 【地名】【人名】【组织结构】【资金】【百分比】【日期】【时间】");
        LOGGER.info("ip: " + CommonUtil.clienIP(request) + " text: " + text + " return: " + CommonUtil.format(1, map));
        return CommonUtil.format(1, map);
    }

    @RequestMapping("sentiment")
    public String sentiment(String text, HttpServletRequest request) throws Exception {
        if (StringUtils.isEmpty(text))
            return CommonUtil.format(0, "输入内容为空");
        if (text.length() > 999999999)
            return CommonUtil.format(0, "输入内容长度最大为 999999999");
        text = text.replace("\n\n", ".\n");
        text = text.replace("..\n", ".\n");
        HashMap<String, Object> map = NLPUtil.getSentiment(text);
        map.put("info", "本接口支持语言：【英文】，可以分析的情感为: 【正面】【中性】【负面】");
        LOGGER.info("ip: " + CommonUtil.clienIP(request) + " text: " + text + " return: " + CommonUtil.format(1, map));
        return CommonUtil.format(1, map);
    }

}

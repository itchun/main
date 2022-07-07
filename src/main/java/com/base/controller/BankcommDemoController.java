package com.base.controller;

import com.bocom.pay.BocomClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/")
public class BankcommDemoController {

    //全局日志记录器
    public static final Logger LOGGER = LogManager.getLogger();

    // 配置文件路径（为了方便演示此处使用了相对路径）
    public static final String CONFIG_FILE_PATH = "/itchun/bocommjava/ini/BocompayMerchant.xml";
    public static BocomClient client = new BocomClient();

    @RequestMapping("notify.do")
    public String ner(HttpServletRequest request) {
        LOGGER.info("request:接受到了通知");
        Map<String, String[]> maps = request.getParameterMap();
        maps.forEach((k, v) -> {
            if (v != null) {
                for (String vv : v) {
                    LOGGER.info(k + ":" + vv);
                }
            } else LOGGER.info(k + ": kong");
        });

        String receiveMessage = request.getParameter("RSASignData");
        receiveMessage = receiveMessage.replace(" ", "+");
        LOGGER.info("receiveMessage:" + receiveMessage);

        // 初始化
        client.initialize(CONFIG_FILE_PATH);

        // 响应报文解签
        String verify = client.AttachedVerify(receiveMessage);
        LOGGER.info("xml:" + verify);

        String MerTranNo = labelFinds(verify, "MerTranNo").get(0);
        LOGGER.info("MerTranNo:" + MerTranNo);
        String message = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Document>\n" +
                "	<Head>\n" +
                "		<RspType>NORMAL</RspType>\n" +
                "		<RspCode>MAPIPY0000</RspCode>\n" +
                "		<RspMsg>交易成功</RspMsg>\n" +
                "		<RspTime>" + getCurrentTime() + "</RspTime>\n" +
                "	</Head>\n" +
                "	<Body>\n" +
                "		<MerTranNo>" + MerTranNo + "</MerTranNo>\n" +
                "	</Body>\n" +
                "</Document>\n";

        // 请求报文加签
        LOGGER.info("signMessage:" + message);
        String signMessage = client.AttachedSign("301140880629503", message);// 测试环境固定使用301140880629503
        LOGGER.info("signMessage to :" + signMessage);
        return signMessage;

    }

    // 商户发送交易时间 格式:yyyyMMddHHmmss
    public static String getCurrentTime() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }

    // 商户发送交易时间 格式:yyyyMMddHHmmss
    public static String getValidPeriod() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 5);
        return new SimpleDateFormat("yyyyMMddHHmmss").format(calendar.getTime());
    }

    // 商户交易编号
    public static String getTranNo() {
        return "Mer" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }

    public static ArrayList<String> labelFinds(String text, String label) {
        Pattern p = Pattern.compile("\\<" + label + "\\>(?<value>.*?)\\<\\/" + label + "\\>");
        Matcher m = p.matcher(text);
        ArrayList<String> values = new ArrayList<>();
        while (m.find()) {
            values.add(m.group("value"));
        }
        return values;
    }
}

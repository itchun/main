package com.base.加密;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Map;

public class AESMain {

    public static void main(String[] args) {

        // oAuth2.0 认证
        String app_key = "65637258";
        String app_secret = "201e70d654ad4478bef860cc2680cc14";

        // 获取token
        String access_token_url = "http://test.age06.com/oauth/oauth2/token?grant_type=client_credentials" +
                "&client_id=" + app_key +
                "&client_secret=" + app_secret;
        String access_token_result = http_get(access_token_url, null, null);
        System.out.println("获取access_token结果--->" + access_token_result);
        String access_token = JSON.parseObject(access_token_result).getString("access_token");

        // 获取 幼儿信息
        String baby_url = "http://test.age06.com/age06v3webapi/api/basic/GetYEXXJH?" +
                "access_token=" + access_token +
                "&BYDBSM=283E6B4C-9DEB-47A1-88B3-E079C5EF34BD&GXSJ=&PageNum=1&PageSize=1";
        String baby_result = http_get(baby_url, null, null);
        System.out.println("获取baby_result结果--->" + baby_result);

        // 获取加密密文
        Integer status = JSON.parseObject(baby_result).getInteger("status");
        String yexxjh = "";
        if (status == 0) {
            yexxjh = JSON.parseObject(baby_result).getString("yexxjh");
        }

        // 解密
        String aec_secret = ")O[NB]6,YF}+efcaj{+oESb9d8>Z'e9M";
        try {
            String content = aesDecry(yexxjh, aec_secret);
            System.out.println("解密后的结果--->" + baby_result.replace("\"" + yexxjh + "\"", content));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("解密失败");
        }
    }

    // 发送http get请求
    public static String http_get(String url, String param, Map<String, Object> heads) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + (StrUtil.isNotBlank(param) ? "?" + param : "");
            URL realUrl = new URL(urlNameString);

            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();

            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            if (heads != null) {
                for (String head : heads.keySet()) {
                    connection.setRequestProperty(head, heads.get(head) == null ? "" : heads.get(head).toString());
                }
            }

            // 建立实际的连接
            connection.connect();

            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }

        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }


    // 解密
    public static String aesDecry(String content, String key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, InvalidAlgorithmParameterException {
        byte[] contentByte = Base64.getDecoder().decode(content);
        byte[] keyByte = key.getBytes();

        //初始化一个密钥对象
        SecretKeySpec keySpec = new SecretKeySpec(keyByte, "AES");

        //初始化一个初始向量,不传入的话，则默认用全0的初始向量
        byte[] initParam = "L+\\~f4,Ir)b$=pkf".getBytes();
        IvParameterSpec ivSpec = new IvParameterSpec(initParam);

        // 指定加密的算法、工作模式和填充方式
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        byte[] result = cipher.doFinal(contentByte);
        return new String(result, "UTF-8");
    }
}

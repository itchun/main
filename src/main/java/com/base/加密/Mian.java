package com.base.加密;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Mian {

    // 汇道API加密
    public static String Encrypt(String app_ID, String APIID, String app_KEY) {
        String str = app_ID + APIID + System.currentTimeMillis() / 1000;
        try {
            app_KEY = app_KEY.replace("-", "");
            byte[] kb = app_KEY.getBytes("utf-8");
            SecretKeySpec sks = new SecretKeySpec(kb, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//算法/模式/补码方式
            cipher.init(Cipher.ENCRYPT_MODE, sks);
            byte[] eb = cipher.doFinal(str.getBytes("utf-8"));
            return new Base64().encodeToString(eb);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
//        String appid = "2b5257d3-6cae-46c1-af0d-452a71b849aa";
//        String appkey = "a4601163-327d-4f87-abd7-59fc1e0a4035";
//        String signKey = "8dfb6392-7198-4285-9412-9b906cadb205";
//
//        String sid = "217c361d-a7ee-4549-9cc9-6f85d8c20142";
//        String APIID = "6bfa95b4-4fec-4d76-89bd-485b7ba97c35"; // apiname

        String appid = "2b5257d3-6cae-46c1-af0d-452a71b849aa";
        String appkey = "a4601163-327d-4f87-abd7-59fc1e0a4035";
        String signKey = "8dfb6392-7198-4285-9412-9b906cadb205";

        String sid = "217c361d-a7ee-4549-9cc9-6f85d8c20142";
        String APIID = "6e9809b6-b9dc-49d3-9a6b-8792cd5d6db8"; // apiname
        System.out.println(Mian.Encrypt(appid,APIID,appkey));

    }
}

package com.base.入园;

import cn.hutool.poi.excel.ExcelReader;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class 入园崇明区字典处理 {

    static String path_in = "D:\\工作\\数据之家\\项目档案\\[学前教育]入园登记系统\\四期\\项目\\报名\\崇明区\\云教提供-崇明幼儿园地段-需确认-反馈信息 - 处理后.xlsx";

    public static void main(String[] args) {
        ExcelReader reader_index0 = new ExcelReader(new File(path_in), 0);
        int sheet_number = reader_index0.getSheetCount();

        // 遍历excel中的sheet
        for (int i = 0; i < sheet_number; i++) {

            // 读取
            ExcelReader reader = new ExcelReader(new File(path_in), i);
            Sheet sheet = reader.getSheet();
            String sheet_name = sheet.getSheetName();
            if (!sheet_name.equals("Sheet2")) continue;
            List<List<Object>> list = reader.read(1);
            List<LinkedHashMap<String, String>> datas = new ArrayList<>();
            for (List<Object> data : list) {
                String 幼儿园 = getStr(data.get(0));
                String 居委 = getStr(data.get(2)).replace(" ", "");
                String 路 = "";
                String 弄开始 = "";
                String 弄结束 = "";
                String 号开始 = "";
                String 号结束 = "";
                String 单双号 = "";
                if (居委.matches(".*?\\d+.*?")) {
                    List<String> regexs = new ArrayList<>();
                    regexs.add("^(.*?)(\\d+)弄(\\d+)-(\\d+)号$");
                    regexs.add("^(.*?)(\\d+)弄-(\\d+)号$");
                    regexs.add("^(.*?)(\\d+)弄(\\d+)号-(\\d+)号");
                    regexs.add("^(.*?)(\\d+)号-(\\d+)号$");
                    regexs.add("^(.*?)(\\d+)-(\\d+)号$");
                    regexs.add("^(.*?)(\\d+)-(\\d+)号([单双])号$");
                    regexs.add("^(.*?)(\\d+)号$");
                    regexs.add("^(.*?)(\\d+)弄$");
                    ok:
                    for (String regex : regexs) {
                        Matcher m = Pattern.compile(regex).matcher(居委);
                        if (m.find()) {
                            switch (regex) {
                                case "^(.*?)(\\d+)弄(\\d+)-(\\d+)号$":
                                    路 = m.group(1);
                                    弄开始 = m.group(2);
                                    弄结束 = m.group(2);
                                    号开始 = m.group(3);
                                    号结束 = m.group(4);
                                    break ok;
                                case "^(.*?)(\\d+)弄-(\\d+)号$":
                                    路 = m.group(1);
                                    弄开始 = m.group(2);
                                    弄结束 = m.group(2);
                                    号开始 = m.group(3);
                                    号结束 = m.group(3);
                                    break ok;
                                case "^(.*?)(\\d+)弄(\\d+)号-(\\d+)号$":
                                    路 = m.group(1);
                                    弄开始 = m.group(2);
                                    弄结束 = m.group(2);
                                    号开始 = m.group(3);
                                    号结束 = m.group(4);
                                    break ok;
                                case "^(.*?)(\\d+)号$":
                                    路 = m.group(1);
                                    号开始 = m.group(2);
                                    号结束 = m.group(2);
                                    break ok;
                                case "^(.*?)(\\d+)弄$":
                                    路 = m.group(1);
                                    弄开始 = m.group(2);
                                    弄结束 = m.group(2);
                                    break ok;
                                case "^(.*?)(\\d+)号-(\\d+)号$":
                                    路 = m.group(1);
                                    号开始 = m.group(2);
                                    号结束 = m.group(3);
                                    break ok;
                                case "^(.*?)(\\d+)-(\\d+)号$":
                                    路 = m.group(1);
                                    号开始 = m.group(2);
                                    号结束 = m.group(3);
                                    break ok;
                                case "^(.*?)(\\d+)-(\\d+)号([单双])号$":
                                    路 = m.group(1);
                                    号开始 = m.group(2);
                                    号结束 = m.group(3);
                                    单双号 = m.group(4) + "号";
                                    break ok;
                                default:
                                    System.out.println(居委);
                            }
                        }
                    }
                    if (路.endsWith("路")) {
                        居委 = "";
                    } else {
                        居委 = 路;
                    }
                }
                if (StringUtils.isEmpty(弄开始)) 弄开始 = null;
                if (StringUtils.isEmpty(弄结束)) 弄结束 = null;
                if (StringUtils.isEmpty(号开始)) 号开始 = null;
                if (StringUtils.isEmpty(号结束)) 号结束 = null;
                System.out.println("insert into cm_kindergarten_dictionary (kindergarten_name,committee_name,road_name,alley_begin_number,alley_end_number,house_begin_number,house_end_number,house_odd_and_even_numbers)" +
                        "values ('" + 幼儿园 + "','" + 居委 + "','" + 路 + "'," + 弄开始 + "," + 弄结束 + "," + 号开始 + "," + 号结束 + ",'" + 单双号 + "');");
            }
        }
    }

    public static String getStr(Object obj) {
        return obj == null ? "" : obj.toString().trim().replace(" ", "");
    }

    public static void main2(String[] args) {
        String address = "弘道中路1号-102号";
        List<String> list = new ArrayList<>();
        list.add("^(.*?)(\\d+)号-(\\d+)号$");
        for (String regex : list) {
            Matcher m = Pattern.compile(regex).matcher(address);
            if (m.find()) {
                System.out.println(regex);
                System.out.println(m.groupCount());
                System.out.println(m.group(1));
                System.out.println(m.group(2));
                System.out.println(m.group(3));
                System.out.println(m.group(4));
            }
        }
    }
}

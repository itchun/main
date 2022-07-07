package com.base.入园;

import cn.hutool.poi.excel.ExcelReader;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class 入园杨浦区字典处理 {

    static String path_in = "D:\\工作\\数据之家\\项目档案\\[学前教育]入园登记系统\\四期\\项目\\报名\\杨浦区\\20220405杨浦幼儿园匹配规则-已调整 - 导入数据库.xls";

    public static void main(String[] args) {
        ExcelReader reader_index0 = new ExcelReader(new File(path_in), 0);
        int sheet_number = reader_index0.getSheetCount();

        // 遍历excel中的sheet
        for (int i = 0; i < sheet_number; i++) {



            // 读取
            ExcelReader reader = new ExcelReader(new File(path_in), i);
            Sheet sheet = reader.getSheet();
            String sheet_name = sheet.getSheetName();
            if (!sheet_name.equals("幼儿园地址库")) continue;
            List<List<Object>> list = reader.read(1);
            for (List<Object> data : list) {
                int index = 0;
                String 幼儿园 = getStr(data.get(index++));
                String 居委 = getStr(data.get(index++)).replace(" ", "");
                String 路 = getStr(data.get(index++)).replace(" ", "");
                String 弄开始 = getStr(data.get(index++)).replace(" ", "").replace("D", "").replace("弄", "");
                String 弄结束 = getStr(data.get(index++)).replace(" ", "").replace("E", "").replace("弄", "");
                String 弄单双号 = getStr(data.get(index++)).replace(" ", "").replace("F", "");
                String 号开始 = getStr(data.get(index++)).replace(" ", "").replace("G", "").replace("号甲", "").replace("甲", "").replace("号", "").replace("乙", "");
                String 号结束 = getStr(data.get(index++)).replace(" ", "").replace("H", "").replace("号甲", "").replace("甲", "").replace("号", "").replace("乙", "");
                String 单双号 = getStr(data.get(index++)).replace(" ", "").replace("I", "");
                String 街道 = getStr(data.get(index++)).replace(" ", "").replace("J", "");
                String 排 = getStr(data.get(index++)).replace(" ", "").replace("K", "");
                if (StringUtils.isEmpty(弄开始)) 弄开始 = null;
                if (StringUtils.isEmpty(弄结束)) 弄结束 = null;
                if (StringUtils.isEmpty(号开始)) 号开始 = null;
                if (StringUtils.isEmpty(号结束)) 号结束 = null;
                if (StringUtils.isEmpty(单双号)) 单双号 = "";
                if (StringUtils.isNotEmpty(单双号) && !单双号.endsWith("号")) 单双号 += "号";
                System.out.println("insert into yp_kindergarten_dictionary (kindergarten_name,street_name,committee_name,road_name,alley_begin_number,alley_end_number,house_begin_number,house_end_number,house_odd_and_even_numbers)" +
                        "values ('" + 幼儿园 + "','" + 街道 + "','" + 居委 + "','" + 路 + "'," + 弄开始 + "," + 弄结束 + "," + 号开始 + "," + 号结束 + ",'" + 单双号 + "');");
            }
        }
    }

    public static String getStr(Object obj) {
        return obj == null ? "" : obj.toString().trim().replace(" ", "");
    }

    public Map<String, String> normalAddress(String address, String pattern) {
        Matcher m = Pattern.compile(pattern).matcher(address);
        if (m.find()) {

        }
        return null;
    }

}

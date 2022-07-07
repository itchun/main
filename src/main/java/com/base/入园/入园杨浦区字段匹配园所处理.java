package com.base.入园;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public class 入园杨浦区字段匹配园所处理 {

    static String path_in = "C:\\Users\\itchun\\Desktop\\杨浦幼儿园地段-v1.0.xlsx";
    static String path_out = "C:\\Users\\itchun\\Desktop\\杨浦幼儿园地段-导出.xlsx";

    public static void main(String[] args) throws Exception {
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
                String 居委 = getStr(data.get(1));
                String 需确认 = getStr(data.get(4)).replace("E", "");
                String 拆分字段 = getStr(data.get(2)).replace("C", "");
                String 原始字段 = getStr(data.get(3)).replace("D", "");
                if (StringUtils.isEmpty(幼儿园)) continue;

                List<String> 拆分字段s = new ArrayList<>();
                if (StringUtils.isEmpty(拆分字段))
                    拆分字段s.add(原始字段);
                else
                    拆分字段s.addAll(Arrays.asList(拆分字段.split("；")));

                String 真实原始字段 = StringUtils.isEmpty(原始字段) ? 拆分字段 : 原始字段;
                for (String 最终拆分字段 : 拆分字段s) {
                    if (StringUtils.isEmpty(最终拆分字段)) continue;
                    if (StringUtils.isEmpty(拆分字段) || !最终拆分字段.contains("、")) {
                        LinkedHashMap<String, String> 对象 = new LinkedHashMap<>();
                        对象.put("幼儿园", 幼儿园);
                        对象.put("居委", 居委);
                        对象.put("具体地址", 真实原始字段);
                        对象.put("拆分字段", StringUtils.isEmpty(拆分字段) ? "" : 最终拆分字段);
                        对象.put("起始弄", "");
                        对象.put("结束弄", "");
                        对象.put("单双号（弄）", "");
                        对象.put("起始号", "");
                        对象.put("结束号", "");
                        对象.put("单双号（路）", "");
                        对象.put("需确认", 需确认);
                        datas.add(对象);
                    } else {
                        String 前缀 = "";
                        if (最终拆分字段.contains("弄"))
                            前缀 = 最终拆分字段.split("弄")[0] + "弄";
                        else if (最终拆分字段.contains("路"))
                            前缀 = 最终拆分字段.split("路")[0] + "路";
                        else if (最终拆分字段.contains("村"))
                            前缀 = 最终拆分字段.split("村")[0] + "村";
                        for (String 号 : 最终拆分字段.replace(前缀, "").split("、")) {
                            if (StringUtils.isEmpty(号)) continue;
                            LinkedHashMap<String, String> 对象 = new LinkedHashMap<>();
                            对象.put("幼儿园", 幼儿园);
                            对象.put("居委", 居委);
                            对象.put("具体地址", 真实原始字段);
                            对象.put("拆分字段", 前缀 + 号 + (号.endsWith("号") ? "" : "号"));
                            对象.put("起始弄", "");
                            对象.put("结束弄", "");
                            对象.put("单双号（弄）", "");
                            对象.put("起始号", "");
                            对象.put("结束号", "");
                            对象.put("单双号（路）", "");
                            对象.put("需确认", 需确认);
                            datas.add(对象);
                        }
                    }
                }
            }

            // 导出
            ExcelWriter writer = ExcelUtil.getWriter(true);
            writer.renameSheet(0, "Sheet1");
            writer.setSheet("Sheet1");
            writer.writeHeadRow(CollUtil.newArrayList("幼儿园", "居委", "具体地址", "拆分字段", "起始弄", "结束弄", "单双号（弄）", "起始号", "结束号", "单双号（路）", "需确认"));
            writer.write(datas);
            File file_out = new File(path_out);
            file_out.deleteOnExit();
            file_out.createNewFile();
            FileOutputStream out = new FileOutputStream(new File(path_out));
            writer.flush(out, true);
            writer.close();
            IoUtil.close(writer);
        }
    }

    public static String getStr(Object obj) {
        return obj == null ? "" : obj.toString().trim().replace(" ", "");
    }
}

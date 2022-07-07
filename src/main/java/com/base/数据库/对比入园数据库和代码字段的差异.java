package com.base.数据库;

import java.io.*;
import java.sql.Connection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class 对比入园数据库和代码字段的差异 {

    public static void main(String[] args) throws Exception {

        // 代码库
        Connection connection_dm = JdbcUtil.get_connection("org.postgresql.Driver", "jdbc:postgresql://192.168.1.227/keirs_2022", "root", "datahome123");
        List<LinkedHashMap<String, Object>> tables_dm = JdbcUtil.findBySql(connection_dm, "select * from pg_tables where schemaname = 'public'");
        List<String> tables_list_dm = tables_dm.stream().map(map -> map.get("tablename").toString()).collect(Collectors.toList());

        // 测试库
        Connection connection_cs = JdbcUtil.get_connection("org.postgresql.Driver", "jdbc:postgresql://172.31.251.36/keirs_2022", "root", "datahome123");
        List<LinkedHashMap<String, Object>> tables_cs = JdbcUtil.findBySql(connection_dm, "select * from pg_tables where schemaname = 'public'");
        List<String> tables_list_cs = tables_cs.stream().map(map -> map.get("tablename").toString()).collect(Collectors.toList());

        // 纠错
        for (String table : tables_list_dm) {
            tables_list_cs.remove(table);

            // 查询表字段
            List<LinkedHashMap<String, Object>> table_dm = JdbcUtil.findBySql(connection_dm, "select * from information_schema.columns where table_schema='public' and table_name='" + table + "'");
            List<LinkedHashMap<String, Object>> table_cs = JdbcUtil.findBySql(connection_cs, "select * from information_schema.columns where table_schema='public' and table_name='" + table + "'");
            Map<String, LinkedHashMap<String, Object>> table_map_dm = new HashMap<>();
            table_dm.stream().forEach(map -> table_map_dm.put(map.get("column_name").toString(), map));
            Map<String, LinkedHashMap<String, Object>> table_map_cs = new HashMap<>();
            table_cs.stream().forEach(map -> table_map_cs.put(map.get("column_name").toString(), map));

            // 比较相同字段的属性
            table_map_dm.keySet().stream().forEach(column_name -> {
                LinkedHashMap<String, Object> column_dm = table_map_dm.get(column_name);
                LinkedHashMap<String, Object> column_cs = table_map_cs.get(column_name);

                column_dm.keySet().stream().forEach(name -> {
                    if (!name.equals("ordinal_position") && !name.equals("dtd_identifier")) {
                        String value_dm = column_dm.get(name) + "";
                        String value_cs = column_cs.get(name) + "";
                        if (!value_dm.equals(value_cs)) {
                            String message = "表:[" + table + "] 字段:[" + column_name + "] 属性[" + name + "] 值不相同，代码中:[" + value_dm + "] 测试库:[" + value_cs + "]";
//                        System.out.println(message);
                            write(message, "比较相同字段的属性.txt");
                        }
                    }
                });
            });

            // 比较多余的字段
            table_map_dm.keySet().stream().forEach(column_name -> table_map_cs.remove(column_name));
            if (!table_map_cs.isEmpty()) {
                table_map_cs.keySet().stream().forEach(column_name -> {
                    String message = "表:[" + table + "] 字段:[" + column_name + "] 测试库是多出来的";
//                    System.out.println(message);
                    write(message, "比较多余的字段.txt");
                });
            }
        }

        // 多余的表
        if (!tables_list_cs.isEmpty()) {
            for (String table : tables_list_cs) {
                String message = "表:[" + table + "] 测试库是多出来的";
//                System.out.println(message);
                write(message, "多余的表.txt");
            }
        }
    }


    // C:\Users\itchun\Desktop\xxx.txt
    public static void write(String str, String fileName) {
        try {
            File file = new File("C:\\Users\\itchun\\Desktop\\" + fileName);
            OutputStream os = new FileOutputStream(file, true);
            OutputStreamWriter osw = new OutputStreamWriter(os, "utf-8");
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(str + "\r\n");
            bw.flush();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.base.数据库.数据库连接非默认模式;

import com.base.数据库.JdbcUtil;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.List;

public class JdbcUtilMain {

    // 本系统数据库
    private static String local_driver = "org.postgresql.Driver";
    private static String local_url = "jdbc:postgresql://192.168.1.224/ocsd?currentSchema=test2";
    private static String local_user = "root";
    private static String local_password = "datahome123";

    // 管理通数据库
    private static String glt_driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//    private static String glt_url = "jdbc:sqlserver://192.168.1.225;instanceName=test3;DatabaseName=kxye";
    private static String glt_url = "jdbc:sqlserver://192.168.1.225//test3;DatabaseName=kxye";
    private static String glt_user = "sa";
    private static String glt_password = "datahome123";

    public static void main(String[] args) throws Exception{
        Connection connection_glt = JdbcUtil.get_connection(local_driver, local_url, local_user, local_password);
        String find_sql = "select * from book2";
        List<LinkedHashMap<String, Object>> data = JdbcUtil.findBySql(connection_glt,find_sql);
        System.out.println(data.size());

//        Connection connection_glt2 = JdbcUtil.get_connection(glt_driver, glt_url, glt_user, glt_password);
//        String find_sql2 = "select * from test2";
//        List<LinkedHashMap<String, Object>> data2 = JdbcUtil.findBySql(connection_glt2,find_sql2);
//        System.out.println(data2.size());
    }
}

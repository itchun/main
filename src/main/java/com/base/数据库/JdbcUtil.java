package com.base.数据库;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public class JdbcUtil {

    // 创建jdbc连接
    public static Connection get_connection(String driver, String url, String user, String password) throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        return DriverManager.getConnection(url, user, password);
    }

    // 关闭连接
    public static void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    // 查询
    public static List<LinkedHashMap<String, Object>> findBySql(Connection con, String sql) throws Exception {
        PreparedStatement pre = con.prepareStatement(sql);
        ResultSet resultSet = pre.executeQuery();
        List<LinkedHashMap<String, Object>> list = new ArrayList<>();
        while (resultSet.next()) {
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnCount = rsmd.getColumnCount();
            LinkedHashMap<String, Object> map = new LinkedHashMap<>();
            for (int i = 1; i <= columnCount; i++) {
                String name = rsmd.getColumnName(i);
                Object value = resultSet.getObject(i);
                map.put(name, value == null ? "" : value.toString().trim());
            }
            list.add(map);
        }
        close(null, pre, resultSet);
        return list;
    }

    // 插入表数据
    public static void insert(Connection con, LinkedHashMap<String, Object> map, String tableName) throws Exception {
        String sql = "insert into " + tableName + " (";

        // 遍历
        Set<String> keySet = map.keySet();
        List<String> keyList = new ArrayList<>();
        for (String name : keySet) {
            sql += name.toLowerCase() + ",";
            keyList.add(name);
        }
        sql = sql.substring(0, sql.length() - 1) + ") values(";
        for (String name : keyList) {
            sql += "?,";
        }
        sql = sql.substring(0, sql.length() - 1) + ");";
        PreparedStatement pre = con.prepareStatement(sql);
        for (int i = 1; i <= keySet.size(); i++) {
            String key = keyList.get(i - 1);
            Object value = map.get(key);
            pre.setObject(i, value == null ? "" : value.toString().trim().toLowerCase());
        }
        pre.executeUpdate();
        close(null, pre, null);
    }

    // 批量插入表数据
    public static void insertbatch(Connection conn, List<LinkedHashMap<String, Object>> datas, String tableName) throws Exception {
        if (datas == null || datas.size() == 0) return;

        // 遍历
        Set<String> keySet = datas.get(0).keySet();
        List<String> keyList = new ArrayList<>();
        String sql = "insert into " + tableName + " (";
        for (String name : keySet) {
            sql += name.toLowerCase() + ",";
            keyList.add(name);
        }
        sql = sql.substring(0, sql.length() - 1) + ") values(";
        for (String name : keyList) {
            sql += "?,";
        }
        sql = sql.substring(0, sql.length() - 1) + ");";
        conn.setAutoCommit(false);
        PreparedStatement pre = conn.prepareStatement(sql);
        for (LinkedHashMap<String, Object> map : datas) {
            for (int i = 1; i <= keySet.size(); i++) {
                String key = keyList.get(i - 1);
                Object value = map.get(key);
                pre.setObject(i, value == null ? "" : value.toString().trim().toLowerCase());
            }
            pre.addBatch();
        }
        pre.executeBatch();
        conn.commit();
        conn.setAutoCommit(true);
        close(null, pre, null);
    }

    // 执行DDL语句
    public static Integer ddl(Connection connection, String sql) throws Exception {
        Statement stmt = connection.createStatement();
        int count = stmt.executeUpdate(sql);
        stmt.close();
        return count;
    }


}

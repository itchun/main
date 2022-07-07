package com.base.字符串;

import java.util.*;

public class 入园遍历 {

    public static void main(String[] args) {

        String road = "";
        String alley_number = "";
        String house_number = "";
        String alley_odd_and_even_numbers = "";
        String streetName = "";
        String committeeName = "";
        String village = "";

        // 准备参数
        LinkedHashMap<String, String> params = new LinkedHashMap<>();
        params.put("streetName", streetName);
        params.put("committeeName", committeeName);
        params.put("village", village);
        params.put("road", road);
        params.put("alley_number", alley_number);
        params.put("alley_odd_and_even_numbers", alley_odd_and_even_numbers);
        Integer max = params.size();

        // 遍历
        for (int i = 0; i < max; i++) {
            Set<Map.Entry<String, String>> set = params.entrySet();
            List<Map.Entry<String, String>> list = new ArrayList<>(set.size());
            list.addAll(set);
            String sql = "select kindergarten_code,kindergarten_name from xh_garden_match where ";
            for (int j = 0; j < list.size(); j++) {
                Map.Entry<String, String> entry = list.get(j);
                String key = entry.getKey();
                String val = entry.getValue();
                if (j == 0)
                    sql += " " + key + " = :" + key + " ";
                else
                    sql += " and " + key + " = :" + key + " ";
                if (j == list.size() - 1) {
                    params.remove(key);
                }
            }
            sql += " group by kindergarten_code,kindergarten_name order by kindergarten_code";
        }
    }
}

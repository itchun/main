package com.base.字符串;

public class Util {

    public static void main(String[] args) {
        uuid_update();
    }

    public static void getsql() {
        String str_info = "user_info,id;" +
                "user_group,id;" +
                "user_info_group,id;" +
                "user_info_group,user_id;" +
                "user_info_group,group_id;" +
                "garden_info,id;" +
                "identity_info,id;" +
                "role_identity,identity_id;" +
                "role_user,id;" +
                "role_user,user_id;" +
                "user_garden,id;" +
                "user_garden,garden_id;" +
                "user_garden,user_id;" +
                "classes_info,id;" +
                "user_classes,id;" +
                "user_classes,classes_id;" +
                "user_classes,user_id;" +
                "user_identity,id;" +
                "user_identity,identity_id;" +
                "user_identity,user_id;" +
                "subscribe_column,id;" +
                "user_subscribe_column,id;" +
                "user_subscribe_column,user_id;" +
                "user_subscribe_column,subscribe_id;" +
                "child_info,id;" +
                "user_child,id;" +
                "user_child,user_id;" +
                "user_child,child_id;" +
                "role_function,id;" +
                "role_function,function_id;";
        for (String str : str_info.split(";")) {
            String[] strs = str.split(",");
            System.out.println("ALTER TABLE \"public\".\"" + strs[0] + "\" ALTER COLUMN \"" + strs[1] + "\" TYPE char(36) COLLATE \"pg_catalog\".\"default\";");
        }
        for (String str : str_info.split(";")) {
            String[] strs = str.split(",");
            System.out.println("update " + strs[0] + " set " + strs[1] + " = (upper(substr(" + strs[1] + ", 1, 8)) ||'-'|| upper(substr(" + strs[1] + ", 9, 4))  ||'-'|| upper(substr(" + strs[1] + ", 13, 4))  ||'-'|| upper(substr(" + strs[1] + ", 17, 4)) ||'-'|| upper(substr(" + strs[1] + ", 21, 12)));");

        }
    }

    public static void uuid_update() {
        // 696FBF11-453A-4C7E-B319-7F62EEE0D097
        // 696fbf11-453a-4c7e-b319-7f62eee0d097
        String uuid = "24fd4179b72b495f9b7b9d3b7b88a111";
        System.out.println((uuid.substring(0, 8) + "-" + uuid.substring(8, 12) + "-" + uuid.substring(12, 16) + "-" + uuid.substring(16, 20)
                + "-" + uuid.substring(20)).toUpperCase());
    }

}

package com.ggs.jdbc;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;

public class JDBCTest03 {
    public static void main(String[] args) {
        Map<String, String> userLoginInfo = initUI();

        boolean loginSuccess = login(userLoginInfo);
        System.out.println(loginSuccess ? "登录成功" : "登录失败");
    }

    /**
     * 用户登录
     *
     * @param userLoginInfo 用户登录信息
     * @return false表示失败，true表示成功
     */
    private static boolean login(Map<String, String> userLoginInfo) {

        boolean b = false;
        ResourceBundle rb = ResourceBundle.getBundle("jdbc");
        String driver = rb.getString("driver");
        String url = rb.getString("url");
        String user = rb.getString("user");
        String password = rb.getString("password");

        Connection c = null;
        Statement s = null;
        ResultSet rs = null;
        try {
            Class.forName(driver);
            c = DriverManager.getConnection(url, user, password);
            s = c.createStatement();
            rs = s.executeQuery("select * from t_user where loginName = '" + userLoginInfo.get("loginName") + "' and loginPwd = '" + userLoginInfo.get("loginPassword") + "'");
            if (rs.next()) {
                b = true;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (s != null) {
                try {
                    s.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return b;
    }

    /**
     * 初始化用户界面
     *
     * @return 用户输入的账号密码
     */
    private static Map<String, String> initUI() {
        Scanner s = new Scanner(System.in);
        System.out.print("用户名：");
        String loginName = s.nextLine();

        System.out.print("密码：");
        String loginPassword = s.nextLine();

        Map<String, String> userLoginInfo = new HashMap<>();
        userLoginInfo.put("loginName", loginName);
        userLoginInfo.put("loginPassword", loginPassword);

        return userLoginInfo;
    }
}

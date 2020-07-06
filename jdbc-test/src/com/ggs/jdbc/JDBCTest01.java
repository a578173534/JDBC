package com.ggs.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class JDBCTest01 {
    public static void main(String[] args) {
        ResourceBundle Bundle = ResourceBundle.getBundle("jdbc");
        String driver = Bundle.getString("driver");
        String url = Bundle.getString("url");
        String user = Bundle.getString("user");
        String password = Bundle.getString("password");

        Connection conn = null;
        Statement stmt = null;
        try {
            //1.注册驱动
            Class.forName(driver);
            //2.获取连接
            conn = DriverManager.getConnection(url, user, password);
            //3.获取数据库操作对象
            stmt = conn.createStatement();
            //4.执行sql语句
            int i = stmt.executeUpdate("delete from dept where deptno = 50");
            System.out.println(i == 1 ? "删除成功" : "删除失败");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}

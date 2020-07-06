package com.ggs.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class JDBCTest06 {
    public static void main(String[] args) {
        ResourceBundle rb = ResourceBundle.getBundle("jdbc");
        String driver = rb.getString("driver");
        String url = rb.getString("url");
        String user = rb.getString("user");
        String password = rb.getString("password");

        Connection c = null;
        PreparedStatement ps = null;
        try {
            //1.注册驱动
            Class.forName(driver);
            //2.获取连接
            c = DriverManager.getConnection(url, user, password);
            //将自动提交机制修改为手动提交
            c.setAutoCommit(false);//开启事务
            //获取预编译的数据库操作对象
            ps = c.prepareStatement("update t_act set balance = ? where actno = ?");
            //给？传值
            ps.setDouble(1, 10000);
            ps.setInt(2, 111);
            ps.executeUpdate();

            ps.setDouble(1, 10000);
            ps.setInt(2, 222);
            ps.executeUpdate();
            //程序能够走到这里说明以上程序没有异常，事务结束，手动提交数据
            c.commit();//提交事务
        } catch (Exception e) {
            //回滚事务
            if (c != null) {
                try {
                    c.rollback();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
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
        }
    }
}

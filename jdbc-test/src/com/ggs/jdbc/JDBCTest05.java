package com.ggs.jdbc;

import java.sql.*;
import java.util.ResourceBundle;

public class JDBCTest05 {
    public static void main(String[] args) {
        ResourceBundle rb = ResourceBundle.getBundle("jdbc");
        String driver = rb.getString("driver");
        String url = rb.getString("url");
        String user = rb.getString("user");
        String password = rb.getString("password");

        Connection c = null;
        PreparedStatement ps = null;
        try {
            Class.forName(driver);
            c = DriverManager.getConnection(url, user, password);

            /*ps = c.prepareStatement("insert into t_user values(?,?,?,?)");
            ps.setInt(1, 3);
            ps.setString(2, "rose");
            ps.setInt(3, 123);
            ps.setString(4, "玫瑰");*/

         /*   ps = c.prepareStatement("delete from t_user where id = ?");
            ps.setInt(1, 3);*/

            ps = c.prepareStatement("update t_user set realName = ?,loginName = ? where id = ?");
            ps.setString(1, "玫瑰1");
            ps.setString(2, "rose1");
            ps.setInt(3, 3);

            ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
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

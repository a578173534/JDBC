import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.Date;

public class Test {
    public static void main(String[] args) {

        ResourceBundle Bundle = ResourceBundle.getBundle("jdbc");
        String driver = Bundle.getString("driver");
        String url = Bundle.getString("url");
        String user = Bundle.getString("user");
        String password = Bundle.getString("password");

        Connection c = null;
        PreparedStatement ps = null;
        try {
            Class.forName(driver);
            c = DriverManager.getConnection(url, user, password);
            // 关闭自动提交事务
            c.setAutoCommit(false); // 事务开始

            String date = "1999-11-20";
            String date2 = "2000-11-20";
            String date3 = "2001-11-20";
            String date4 = "1998-11-20";
            String date5 = "1999-1-20";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date d = sdf.parse(date);
            Date d2 = sdf.parse(date2);
            Date d3 = sdf.parse(date3);
            Date d4 = sdf.parse(date4);
            Date d5 = sdf.parse(date5);

            java.sql.Date sqlDate = new java.sql.Date(d.getTime());
            java.sql.Date sqlDate2 = new java.sql.Date(d2.getTime());
            java.sql.Date sqlDate3 = new java.sql.Date(d3.getTime());
            java.sql.Date sqlDate4 = new java.sql.Date(d4.getTime());
            java.sql.Date sqlDate5 = new java.sql.Date(d5.getTime());

            ps = c.prepareStatement("insert into kuangxianfa values(?,?,?,?,?,?)");
            ps.setString(1, "张三");
            ps.setDate(2, sqlDate);
            ps.setString(3, "男");
            ps.setString(4, "广东省");
            ps.setString(5, "东莞市");
            ps.setString(6, "123456");
            ps.executeUpdate();

            ps.setString(1, "李四");
            ps.setDate(2, sqlDate2);
            ps.setString(3, "女");
            ps.setString(4, "广东省");
            ps.setString(5, "广州市");
            ps.setString(6, "123456");
            ps.executeUpdate();

            ps.setString(1, "王五");
            ps.setDate(2, sqlDate3);
            ps.setString(3, "男");
            ps.setString(4, "广东省");
            ps.setString(5, "深圳市");
            ps.setString(6, "123456");
            ps.executeUpdate();

            ps.setString(1, "张三啊啊啊");
            ps.setDate(2, sqlDate4);
            ps.setString(3, "男");
            ps.setString(4, "广东省");
            ps.setString(5, "韶关市");
            ps.setString(6, "123456");
            ps.executeUpdate();

            ps.setString(1, "张三666");
            ps.setDate(2, sqlDate5);
            ps.setString(3, "女");
            ps.setString(4, "广东省");
            ps.setString(5, "惠州市");
            ps.setString(6, "123456");
            ps.executeUpdate();
            // 提交事务
            c.commit();
        } catch (Exception e) {
            if (c != null) {
                try {
                    // 事务回滚
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

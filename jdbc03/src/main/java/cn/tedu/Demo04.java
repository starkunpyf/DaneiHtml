package cn.tedu;

import java.sql.*;
import java.util.Scanner;

public class Demo04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter username");
        String username = scanner.nextLine();
        System.out.println("please enter password");
        String password = scanner.nextLine();
        try (Connection conn = DBUtils.getConn()){

            String sql = "select count(*) from user where username=? and password=?";
            System.out.println(sql);
            PreparedStatement ps  = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            //结果集对象 游标默认没有指向任何一条数据
            rs.next(); //游标下移指向查询到的数量
            int count = rs.getInt(1);
            if (count>0){
                System.out.println("登录成功");
            }else System.out.println("用户名或密码错误!");


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

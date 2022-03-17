package cn.tedu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Demo06 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名");
        String username = sc.nextLine();
        System.out.println("请输入密码");
        String password = sc.nextLine();
        System.out.println("请输入昵称");
        String nick = sc.nextLine();
        //获取连接
        try (Connection conn = DBUtils.getConn()){
            //查询用户名是否存在
            String sql = "select id from user where username=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                System.out.println("用户名已存在!");
                return;//结束方法
            }
            //代码继续执行说明没有注册过
            String regSql = "insert into user values(null,?,?,?)";
            PreparedStatement regPs = conn.prepareStatement(regSql);
            regPs.setString(1,username);
            regPs.setString(2,password);
            regPs.setString(3,nick);
            regPs.executeUpdate();//执行注册
            System.out.println("注册完成!");


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}

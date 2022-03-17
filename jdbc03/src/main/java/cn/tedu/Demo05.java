package cn.tedu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Demo05 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter username");
        String username = scanner.nextLine();
        System.out.println("please enter password");
        String password = scanner.nextLine();
        try (Connection conn = DBUtils.getConn()){
            String sql = "select password from user where username=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            //替换掉?
            ps.setString(1,username);
            //执行查询
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String pw = rs.getString("password");
                if(pw.equals(password)){
                    System.out.println("succeed");
                }else{
                    System.out.println("pw wrong");
                }
            }else{
                System.out.println("name wrong");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

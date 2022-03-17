package cn.tedu.boot21.controller;

import cn.tedu.boot21.entity.User;
import cn.tedu.boot21.utils.DBUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@RestController
public class UserController {
    @RequestMapping("/reg")
    public void register(User user){
        try (Connection conn = DBUtils.getConn()){
            //查询用户名是否存在
            String sql = "select id from user where username=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,user.getUsername());
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                System.out.println("用户名已存在!");
                return;//结束方法
            }
            //代码继续执行说明没有注册过
            String regSql = "insert into user values(null,?,?,?)";
            PreparedStatement regPs = conn.prepareStatement(regSql);
            regPs.setString(1,user.getUsername());
            regPs.setString(2,user.getPassword());
            regPs.setString(3,user.getNick());
            regPs.executeUpdate();//执行注册
            System.out.println("注册完成!");


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @RequestMapping("/login")
    public String login(User user) {
        try (Connection conn = DBUtils.getConn()) {
            //查询用户名是否存在
            String sql = "select password from user where username=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String pw = rs.getString(1);
                if (pw.equals(user.getPassword())) {
                    return "登录成功";
                } else {
                    return "密码错误!";
                }
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "用户名不存在!";
    }
}

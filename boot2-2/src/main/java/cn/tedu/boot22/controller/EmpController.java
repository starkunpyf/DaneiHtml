package cn.tedu.boot22.controller;

import cn.tedu.boot22.entity.Emp;
import cn.tedu.boot22.utils.DBUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.ArrayList;

@RestController
public class EmpController {
    @RequestMapping("/insert")
    public String insert(Emp emp){
        System.out.println("emp = " + emp);
        //获取数据库连接 把员工信息保存到数据库myemp表中
        try (Connection conn = DBUtils.getConn()){
            String sql = "insert into myemp values(null,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,emp.getName());
            ps.setInt(2,emp.getSal());
            ps.setString(3,emp.getJob());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "添加完成!";
    }

    @RequestMapping("/select")
    public String select(){
        //获取连接 查询所有员工信息
        ArrayList<Emp> list = new ArrayList<>();
        try (Connection conn = DBUtils.getConn()){
            String sql = "select * from myemp";
            Statement s = conn.createStatement();
            //执行查询
            ResultSet rs = s.executeQuery(sql);
            //遍历结果集对象
            while(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int sal = rs.getInt(3);
                String job = rs.getString(4);
                //把员工信息封装到实例类中
                Emp e = new Emp();
                e.setId(id);
                e.setName(name);
                e.setSal(sal);
                e.setJob(job);
                //把员工对象装进list集合
                list.add(e);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String html = "<table border=1>";
        html+="<caption>员工列表</caption>";
        html+="<tr><th>id</th><th>名字</th><th>工资</th><th>工作</th></tr>";
        //把查询出来的员工信息添加到表格中
        for (Emp e:list) {
            html+="<tr>";
            html+="<td>"+e.getId()+"</td>";
            html+="<td>"+e.getName()+"</td>";
            html+="<td>"+e.getSal()+"</td>";
            html+="<td>"+e.getJob()+"</td>";
            html+="<td><a href='/delete?id="+e.getId()+"'>删除</a></td>";
            html+="</tr>";
        }
        html+="</table>";
        return html;
    }

    @RequestMapping("/delete")
    public String delete(int id){
        System.out.println("id = " + id);
        //获取连接 执行删除的SQL语句
        try (Connection conn = DBUtils.getConn()){
            String sql = "delete from myemp where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "删除成功";
    }
}

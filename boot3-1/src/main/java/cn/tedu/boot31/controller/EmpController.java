package cn.tedu.boot31.controller;

import cn.tedu.boot31.entity.Emp;
import cn.tedu.boot31.mapper.EmpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmpController {
    //把Mapper装配进来
    @Autowired(required = false)
    EmpMapper mapper;

    @RequestMapping("/insert")
    public String insert(Emp emp){
        mapper.insert(emp);
        return "添加完成!";
    }

    @RequestMapping("/select")
    public String select(){
        List<Emp> list = mapper.select();
//        **********************此处代码可以复制粘贴********************
        String html = "<table border=1>";
        html+="<caption>员工列表</caption>";
        html+="<tr><th>id</th><th>名字</th><th>工资</th><th>工作</th><th>操作</th></tr>";
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
        //        **********************此处代码可以复制粘贴********************
        return html;
    }
    @RequestMapping("/delete")
    public String delete(int id){
        mapper.deleteById(id);
        return "删除完成!<a href='/select'>返回列表页面</a>";
    }

    @RequestMapping("/update")
    public String update(Emp emp){
        System.out.println("emp = " + emp);
        mapper.update(emp);
        return "修改完成!<a href='/select'>返回列表页面</a>";
    }
}

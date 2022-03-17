package cn.tedu.boot23.controller;

import cn.tedu.boot23.entity.Emp;
import cn.tedu.boot23.mapper.EmpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmpController {
    //Spring框架+Mybatis框架会自动根据此接口创建一个实现类并实例化该对象
    @Autowired(required = false)    //required=false 设置为非必须的对象
            EmpMapper mapper;

    @RequestMapping("/insert")
    public String insert(Emp emp){
        System.out.println("emp = " + emp);
        //调用mapper里面的方法,此时这个方法已经被Mybatis框架实现
        mapper.insert(emp);
        return "添加完成!";
    }
}

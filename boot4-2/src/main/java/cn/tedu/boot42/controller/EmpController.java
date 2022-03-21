package cn.tedu.boot42.controller;

import cn.tedu.boot42.entity.Emp;
import cn.tedu.boot42.mapper.EmpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmpController {
    @Autowired(required = false)
    EmpMapper mapper;

    @RequestMapping("/insert")
    public void insert(Emp emp){
        mapper.insert(emp);
    }
    @RequestMapping("/select")
    public List<Emp> select(){
        return mapper.select();
    }
    @RequestMapping("/delete")
    public void delete(int id){
        mapper.deleteById(id);
    }
    @RequestMapping("/selectById")
    public Emp selectById(int id) {
        return mapper.selectById(id);

    }

    @RequestMapping("/update")
    public void update(Emp emp) {
        mapper.update(emp);

    }
}

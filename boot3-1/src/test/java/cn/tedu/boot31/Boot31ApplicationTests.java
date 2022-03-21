package cn.tedu.boot31;

import cn.tedu.boot31.entity.Emp;
import cn.tedu.boot31.mapper.EmpMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Boot31ApplicationTests {
    //把Mapper装配进来
    @Autowired(required = false)
    EmpMapper mapper;
    @Test
    void contextLoads() {
        Emp e = new Emp();
        e.setName("诸葛亮");
        e.setSal(666);
        e.setJob("军师");
        mapper.insert(e);
    }

    @Test
    void test01(){
        List<Emp> list = mapper.select();
        for (Emp e:list) {
            System.out.println(e.getName()+":"+e.getSal()+":"+e.getJob());
        }
    }
    @Test
    void test02(){
        Emp e = mapper.selectById(2);
        System.out.println(e);
    }

}

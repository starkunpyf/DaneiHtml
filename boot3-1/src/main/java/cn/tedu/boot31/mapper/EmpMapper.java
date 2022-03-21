package cn.tedu.boot31.mapper;

import cn.tedu.boot31.entity.Emp;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmpMapper {
    @Insert("insert into myemp values(null,#{name},#{sal},#{job})")
    void insert(Emp emp);


    //@Select是查询的注解,返回值为集合,Mybatis框架生成的jdbc代码会
    //将查询到的数据封装到Emp对象中,然后把Emp对象装进list集合 通过方法返回
    @Select("select * from myemp")
    List<Emp> select();

    //如果查询带条件 而且查询结果是1个对象时使用下面写法
    @Select("select * from myemp where id=#{id}")
    Emp selectById(int id);

    //如果查询到的结果有多条数据 用一个对象作为返回会报错, 应该用集合
    @Select("select * from myemp where name=#{name}")
    List<Emp> selectByName(String name);

    //方法返回值为int时 返回的是生效的行数
    @Update("update myemp set name=#{name},sal=#{sal},job=#{job} where id=#{id}")
    int update(Emp emp);

    //删除注解
    @Delete("delete from myemp where id=#{id}")
    void deleteById(int id);
}

package cn.tedu.boot42.mapper;

import cn.tedu.boot42.entity.Emp;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmpMapper {
    @Insert("insert into myemp values(null,#{name},#{sal},#{job})")
    void insert(Emp emp);

    @Delete("delete from myemp where id=#{id}")
    void deleteById(int id);

    @Update("update myemp set name=#{name},sal=#{sal},job=#{job} where id=#{id}")
    void update(Emp emp);

    @Select("select * from myemp")
    List<Emp> select();

    @Select("select * from myemp where id=#{id}")
    Emp selectById(int id);
}

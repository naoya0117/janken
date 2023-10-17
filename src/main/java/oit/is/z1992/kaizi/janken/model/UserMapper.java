package oit.is.z1992.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT id, name from users where id = #{id}")
    User selectById(String id);
    @Select("SELECT id, name from users where name = #{name}")
    User selectByName(String name);
    @Select("SELECT id, name from users")
    ArrayList<User> selectAllUsers();
}
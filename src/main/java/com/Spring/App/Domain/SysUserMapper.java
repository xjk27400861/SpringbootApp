package com.Spring.App.Domain;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Results;

import com.Spring.App.Model.*;

//MyBatis注解操作
@Mapper
public interface SysUserMapper {
	    @Select("SELECT id,cnname,email,enabled,user_name as UserName,pass_word as PassWord FROM sys_user WHERE user_name = #{name}")
	    Sys_User findByName(@Param("name") String name);
	    
	    @Insert("INSERT INTO sys_user(user_name, pass_word) VALUES(#{name}, #{pwd})")
	    int insert(@Param("name") String name, @Param("pwd") String pwd);
	    
	    @Insert("INSERT INTO sys_user(user_name, pass_word) VALUES(#{name,jdbcType=VARCHAR}, #{pwd,jdbcType=VARCHAR})")
	    int insertByMap(Map<String, Object> map);
	    //使用方法
	    //Map<String, Object> map = new HashMap<>();
	    //map.put("name", "CCC");上
	    //map.put("age", 40);
	    //userMapper.insertByMap(map)
	    
	    @Insert("INSERT INTO sys_user(user_name, pass_word) VALUES(#{username}, #{password})")
	    int insertByUser(Sys_User user);
	    
	    @Update("UPDATE sys_user SET pass_word=#{password} WHERE user_name=#{username}")
	    void update(Sys_User user);
	    
	    @Delete("DELETE FROM sys_user WHERE id =#{id}")
	    void delete(Long id);
	    
	    @Results({
	        @Result(property = "name", column = "user_name"),
	        @Result(property = "age", column = "pass_word")
	    })
	    @Select("SELECT user_name, pass_word FROM sys_user")
	    List<Sys_User> findAll();
}

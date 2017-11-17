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
public interface SysPermissionMapper {
	@Select("select p.*,p.module_name as ModuleName " + 
			"        from sys_user u" + 
			"        LEFT JOIN sys_user_role sru on u.id= sru.user_id" + 
			"        LEFT JOIN sys_role r on sru.role_id=r.id" + 
			"        LEFT JOIN sys_role_permission spr on spr.role_id=r.id" + 
			"        LEFT JOIN sys_permission p on p.id =spr.permission_id" + 
			"        where u.id=#{userId}")
    List<Sys_Permission> findByName(@Param("userId") Long userid);
    
    @Select("SELECT *,module_name as ModuleName from sys_permission")
    List<Sys_Permission> findAll();
}

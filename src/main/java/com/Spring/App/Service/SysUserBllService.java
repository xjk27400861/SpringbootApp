package com.Spring.App.Service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.Spring.App.Model.Sys_User;

public interface SysUserBllService {
	List<Sys_User> getlist();
	
	public Page<Sys_User> getpageList(int page,int size,String name,String email,String cnname,String gender);
}

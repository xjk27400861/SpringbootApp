package com.Spring.App.ServiceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Spring.App.Domain.*;
import com.Spring.App.Model.Sys_User;
import com.Spring.App.Service.SysUserBllService;

import org.springframework.data.domain.*;
@Service
public class SysUserBllServiceImp implements SysUserBllService {
	@Autowired
	private SysUserRepository sysuserMpp;
	
	public List<Sys_User> getlist() {
		return sysuserMpp.findAll();
	}
	
	public Page<Sys_User> getpageList(int page,int size,String name,String email,String cnname,String gender)
	{
		Sort sort = new Sort(Sort.Direction.ASC, "id"); 
		
		//int size=1;
	    Pageable pageable = new PageRequest(page, size, sort);
		return sysuserMpp.selectAll(name,email,cnname,gender,pageable);
	}
}

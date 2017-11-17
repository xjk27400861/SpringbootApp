package com.Spring.App.ServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.Spring.App.Domain.UserMapper;
import com.Spring.App.Model.User;
import com.Spring.App.Service.UserBllService;

@Service
public class UserBLLServiceImp implements UserBllService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Transactional
    public User login(String name, String password)
    {
		User item = userMapper.findByName(name);
		return item;
    }
	
	@Transactional(isolation=Isolation.DEFAULT,propagation = Propagation.REQUIRED)
	public void AddUser(User entity)
	{
		userMapper.insertByUser(entity);
	}
}

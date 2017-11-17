package com.Spring.App.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.Spring.App.Domain.UserMapper;
import com.Spring.App.Model.User;

public interface UserBllService {
	
	@Transactional
    User login(String name, String password);
	
	@Transactional(isolation=Isolation.DEFAULT,propagation = Propagation.REQUIRED)
	void AddUser(User entity);
}

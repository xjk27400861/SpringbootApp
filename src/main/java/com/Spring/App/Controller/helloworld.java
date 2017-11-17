package com.Spring.App.Controller;

import java.util.List;

import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

import com.Spring.App.Domain.UserRepository;
import com.Spring.App.Model.User;

@RestController
@Api("UserController相关api")
public class helloworld {
	//注意Autowired注入的使用：http://blog.csdn.net/huihuilovei/article/details/62041734
	@Autowired
	private UserRepository _userRep;
	
	@RequestMapping("/hello")
    public String index() {
        return "Hello World";
    }
	
	@RequestMapping("/hellogetUser")
    public List<User> getAllUser() {
		List<User> lst = _userRep.findAll();
		return lst;
        //return "Hello World";
    }
}

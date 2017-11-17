package com.Spring.App.Controller;

import javax.print.DocFlavor.STRING;
import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

import com.Spring.App.MyException;

import io.swagger.annotations.Api;

import com.Spring.App.Domain.*;
import com.Spring.App.Model.Sys_User;
import org.springframework.cache.CacheManager;
import com.Spring.App.Core.*;

import org.springframework.data.redis.core.StringRedisTemplate;

import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import javax.servlet.http.*;
import org.springframework.util.*;
import org.springframework.security.web.authentication.logout.*;
@Controller
@EnableAutoConfiguration
@ComponentScan
@RequestMapping("/")
public class Index {
	
	@Autowired
	SysUserRepository _rep;
	
	@Autowired
    private RedisUtils redisUtils;
	
//	@Autowired
//    private StringRedisTemplate stringRedisTemplate;
//	
//	@Autowired
//    private RedisTemplate redisTemplate;
//	
//	@Autowired
//	private CacheManager cacheManager;
	
	@RequestMapping("/default")
    public String index(ModelMap map) {
		// 加入一个属性，用来在模板中读取
        map.addAttribute("host", "http://blog.didispace.com");
        
        //spring security 获取当前用户信息
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
        	    .getAuthentication()
        	    .getPrincipal();
        map.addAttribute("userName", userDetails.getUsername());
        // return模板文件的名称，对应src/main/resources/templates/index.html
        return "/index";  
        //ModelAndView mv = new ModelAndView("index");
        //return mv;
    }
	
	//@GetMapping是一个组合注解，是@RequestMapping(method = RequestMethod.GET)的缩写。
	@GetMapping("/system")
    public ModelAndView test(ModelMap map) throws Exception {
        // 加入一个属性，用来在模板中读取
        map.addAttribute("host", "http://blog.didispace.com");
         
        ModelAndView mv = new ModelAndView("/System/index");
        
        return mv;
    }
	
	//统一异常处理
	@RequestMapping("/commonError")
	public ModelAndView commonError(ModelMap map) throws Exception{
		//通用error
		throw new Exception("发生错误"); 
	}
	
	//自定义异常处理
	//@ResponseBody
	@RequestMapping("/jsonError")
	public ModelAndView jsonError(ModelMap map) throws MyException{
		//自定义error
		throw new MyException("发生错误"); 
	}
	
	@RequestMapping("/login")
	public String login() {
		Sys_User user = _rep.findByUsername("admin");
		System.out.println(user.getCNname());
		user.setCNname("xujk");
		_rep.save(user);
		System.out.println(user.getCNname());
		//_rep.findAll();
		
		//redis值获取
//		stringRedisTemplate.opsForValue().set("all", "111");
//		String st = stringRedisTemplate.opsForValue().get("all");
		
//		Sys_User item = new Sys_User();
//		item.setId(3L);
//		item.setCNname("xujk001");
//		item.setUsername("xujk001");
//		item.setPassword("xujk001");
//		item.setEnabled(true);
//		Sys_User returnValue = _rep.save(item);
//		
//		redisUtils.set("Users::xujk002", "徐敬昆");
		//_rep.delete(returnValue);
//		String tl1= redisUtils.get("Users::xujk002").toString();
//		System.out.println(tl1);
		
		
		return "/login";
		/*ModelAndView mv = new ModelAndView("/login");
        
        return mv;*/
	}
	
	@RequestMapping("/index")
	public String index_1() 
	{
		return "/index_1"; 
	}
	
	@RequestMapping("/403")
    public ModelAndView error(){
		ModelAndView mv = new ModelAndView("/403");
        
        return mv;
        //return "403";
    }
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
	{
	     Assert.notNull(request, "HttpServletRequest required");
	     
	     //method --1
//	     //if (this.invalidateHttpSession) {
//	         HttpSession session = request.getSession(false);
//	         if (session != null) {
//	              session.invalidate(); //使当前会话失效
//	         }
//	         Cookie[] cookie = request.getCookies();
//	         if(cookie !=null && cookie.length>0)
//	         {
//	        	 for (Cookie item : cookie) {
//					item.setValue(null);
//				    item.setMaxAge(0);
//				    item.setPath("/");
//			        response.addCookie(item);
//				}
//	         }
//	     //}

//	     SecurityContextHolder.clearContext(); //清空安全上下文
	     
	     //method --2
	     Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	     if (auth != null){    
	         new SecurityContextLogoutHandler().logout(request, response, auth);
	     }
	     
	     return "/login";
	}
}

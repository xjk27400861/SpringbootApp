package com.Spring.App;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.annotation.Resource;
import javax.persistence.Embeddable;
import javax.sql.DataSource;

import org.springframework.security.core.userdetails.UserDetailsService;
import com.Spring.App.Security.*;

import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
    private MyFilterSecurityInterceptor myFilterSecurityInterceptor;
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/","/login",
                		"/css/**",
                        "/img/**",
                        "/js/**",
                        "/partial/**",
                        "/script/**",
                        "/upload/**",
                        "/plugin/**",
                        "/content/**",
                        "/scripts/**",
                        "/ViewModel/**").permitAll()//不需要身份验证
                .anyRequest().authenticated()//其余的都需要身份验证
                .and()
            .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/default").failureUrl("/login?error")
                .usernameParameter("name")
                .passwordParameter("password")
                .permitAll()
                .and()
            .logout()
            .logoutUrl("/logout")
                .permitAll()
                .logoutSuccessUrl("/login")
                .and()
            .csrf().disable()//跨域访问攻击
//            .and()
//            .sessionManagement()
//            .maximumSessions(1)//只能登陆一次
            //.and()
            .rememberMe()
            .tokenValiditySeconds(604800)//记住我功能，cookies有限期是一周
            .rememberMeParameter("remember-me")//登陆时是否激活记住我功能的参数名字，在登陆页面有展示
            .rememberMeCookieName("workspace");//cookies的名字，登陆后可以通过浏览器查看cookies名字;
        
        //http请求Filter拦截，Authorization（授权）
        http.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class);
    }
    
    @Resource
    private DataSource dataSource;
    
    @Bean
    UserDetailsService customUserService(){ //注册UserDetailsService 的bean
        return new CustomUserService();
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// 1.内存简单验证方法
		// auth
		// .inMemoryAuthentication()
		// .withUser("user").password("password").roles("USER");
    	
    	//2.jdbc数据库连接验证--需要按照标准定义table，暂不用
    	//http://www.cnblogs.com/hongxf1990/p/6555848.html
    	/*auth.jdbcAuthentication().dataSource(dataSource)
    	.usersByUsernameQuery("select name,age,'true' from user where name=?") 
    	.authoritiesByUsernameQuery("select username,'Admin' from user_role where username=?"); */
    	
    	//3.userDetailsService方式验证（role角色表字段值必须以ROLE_开头）
    	auth.userDetailsService(customUserService()); //user Details Service验证
    }
}
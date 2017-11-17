package com.Spring.App.Security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DbConfig {
	@Value("${spring.datasource.url}")
    private String dbUrl;
	
	@Value("${spring.datasource.username}")
    private String uname;
	
	@Value("${spring.datasource.password}")
    private String pwd;
	
	@Value("${spring.datasource.driver-class-name}")
    private String driver;
	
	@Bean(name = "dataSource")
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(driver);//("com.mysql.jdbc.Driver");
        driverManagerDataSource.setUrl(dbUrl);//("jdbc:mysql://11.2.253.229:3308/SpringMVCDemo?characterEncoding=utf8&useSSL=false");
        driverManagerDataSource.setUsername(uname);//("root");
        driverManagerDataSource.setPassword(pwd);//("123456");
        return driverManagerDataSource;
    }
}

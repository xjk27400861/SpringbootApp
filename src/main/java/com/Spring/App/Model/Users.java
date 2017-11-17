package com.Spring.App.Model;

public class Users {
	private Long id; 
    private String name; 
    private Integer age;
    
    public void setid(long id)
    {
    	this.id=id;
    }
    
    public long getid()
    {
    	return id;
    }
    
    public void setname(String name)
    {
    	this.name=name;
    }
    
    public String getname()
    {
    	return name;
    }
    
    public void setage(Integer age)
    {
    	this.age=age;
    }
    
    public Integer getage()
    {
    	return age;
    }
}

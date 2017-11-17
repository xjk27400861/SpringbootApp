package com.Spring.App.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserRole {
	@Id
    @GeneratedValue
    @Column(nullable = false)
    private Long userid;
	
	@Column(nullable = false)
	private String username;

    @Column(nullable = false)
    private Long roleid;
    
    public UserRole(){}
    
    public UserRole(Long userid, Long roleid,String username) {
        this.userid = userid;
        this.roleid = roleid;
        this.username=username;
    }
    
    public long getRoleId() {return roleid;}
    public void setRoleId(Long role) {this.roleid=role;}
    
    public long getUserId() {return userid;}
    public void setUserId(Long user) {this.userid=user;}
    
    public String getUserName() {return username;}
    public void setUserName(String name) {this.username=name;}
}

package com.Spring.App.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


import java.io.Serializable;
@Entity
@Table(name="sys_user")
public class Sys_User implements Serializable {
	@Id
    @GeneratedValue
	private Long id;
	
	@Column(name="UserName",nullable = false)
	private String username;
	
	@Column(name="PassWord",nullable = false)
	private String password;
	
	@Column(nullable=false,columnDefinition="bool default true")
	private Boolean Enabled;
	
	@Column(nullable=true)
	private String Email;
	
	@Column(nullable = true)
	private String CNname;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return Enabled;
	}

	public void setEnabled(Boolean enabled) {
		Enabled = enabled;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getCNname() {
		return CNname;
	}

	public void setCNname(String cNname) {
		CNname = cNname;
	}
}

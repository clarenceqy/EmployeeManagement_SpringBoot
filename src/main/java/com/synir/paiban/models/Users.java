package com.synir.paiban.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;


@Entity
@Table(name = "Users")
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "用户名不能为空")
	private String username;
	
	@NotBlank(message = "姓名不能为空")
	private String name;
	
	@Length(min= 6,message = "密码长度最短6位")
	private String password;
	
	@Min(value = 1,message = "权限最大1")
	@Max(value = 5,message = "权限最小5")
	private int adminlevel;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return this.username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAdminlevel() {
		return adminlevel;
	}
	public void setAdminlevel(int adminlevel) {
		this.adminlevel = adminlevel;
	}
}

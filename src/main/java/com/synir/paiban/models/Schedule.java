package com.synir.paiban.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Schedule")
public class Schedule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String uuid;
	private String title;
	private String year;
	private String month;
	private String creatorusername;
	private String creatorname;
	private String targetusername;
	private String targetname;
	private String timestamp;
	//private int col;
	private int adminlevel;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getCreatorusername() {
		return creatorusername;
	}
	public void setCreatorusername(String creatorusername) {
		this.creatorusername = creatorusername;
	}
	public String getCreatorname() {
		return creatorname;
	}
	public void setCreatorname(String creatorname) {
		this.creatorname = creatorname;
	}
	public String getTargetusername() {
		return targetusername;
	}
	public void setTargetusername(String targetusername) {
		this.targetusername = targetusername;
	}
	public String getTargetname() {
		return targetname;
	}
	public void setTargetname(String targetname) {
		this.targetname = targetname;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
//	public int getCol() {
//		return col;
//	}
//	public void setCol(int col) {
//		this.col = col;
//	}
	public int getAdminlevel() {
		return adminlevel;
	}
	public void setAdminlevel(int adminlevel) {
		this.adminlevel = adminlevel;
	}
	
	public void setattr(String title,String year,String month,String creatorusername,String creatorname,String timestamp,String uuid,int adminlevel) {
		this.title = title;
		this.year = year;
		this.month = month;
		this.creatorusername = creatorusername;
		this.creatorname = creatorname;
		this.timestamp = timestamp;
		this.uuid = uuid;
		this.adminlevel = adminlevel;
	}
	
}

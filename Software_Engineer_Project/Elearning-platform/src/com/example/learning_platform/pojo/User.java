package com.example.learning_platform.pojo;

import java.util.HashSet;
import java.util.Set;

public class User {

	public static final int MALE = 0;
	public static final int FEMALE = 1;

	private Long id;
	private String userName;
	private String password;
	private int sex = MALE;
	private String email;
	private String registerTime;
	private Set<LearningGroup> learningGroups = new HashSet<LearningGroup>();// 用户下载的资源

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}


	public Set<LearningGroup> getLearningGroups() {
		return learningGroups;
	}

	public void setLearningGroups(Set<LearningGroup> learningGroups) {
		this.learningGroups = learningGroups;
	}

}

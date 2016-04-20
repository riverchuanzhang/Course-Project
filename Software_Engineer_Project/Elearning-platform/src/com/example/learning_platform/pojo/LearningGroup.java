package com.example.learning_platform.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 学习组
 * 
 * @author zdc
 * 
 */
public class LearningGroup implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 548651934270621686L;
	private Long id;
	private String intro;// 简介
	private String name;// 组名

	private User creater;
	private Set<User> users = new HashSet<>();
	
	private List<Paper> papers=new ArrayList<Paper>();
	private List<Question> questions=new ArrayList<Question>();
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getCreater() {
		return creater;
	}

	public void setCreater(User creater) {
		this.creater = creater;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public List<Paper> getPapers() {
		return papers;
	}

	public void setPapers(List<Paper> papers) {
		this.papers = papers;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

}

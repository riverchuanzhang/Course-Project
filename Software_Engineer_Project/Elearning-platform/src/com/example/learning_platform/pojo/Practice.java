package com.example.learning_platform.pojo;

import java.io.Serializable;

public class Practice implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7913410046516374790L;

	private Long id;
	private int score;
	private String time;
	private User creater;
	private Paper paper;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Paper getPaper() {
		return paper;
	}

	public void setPaper(Paper paper) {
		this.paper = paper;
	}

	public User getCreater() {
		return creater;
	}

	public void setCreater(User creater) {
		this.creater = creater;
	}
}

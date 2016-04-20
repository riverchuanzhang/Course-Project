package com.example.learning_platform.pojo;

import java.io.Serializable;

/**
 * 对问题的回答
 * 
 * @author zdc
 * 
 */
public class Answer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2929634828009255041L;

	private Long id;
	private String detail;
	private String createTime;
	private User creater;
	private Question question;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public User getCreater() {
		return creater;
	}

	public void setCreater(User creater) {
		this.creater = creater;
	}

}

package com.example.learning_platform.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 试卷
 * 
 * @author zdc
 * 
 */
public class Paper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2404691125550624049L;

	private Long id;
	private String title;
	private String intro;
	private User creater;
	private LearningGroup learningGroup;
	private List<Topic> topics = new ArrayList<Topic>();
	private String createTime;
	private List<Practice> practices = new ArrayList<Practice>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getCreater() {
		return creater;
	}

	public void setCreater(User creater) {
		this.creater = creater;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public LearningGroup getLearningGroup() {
		return learningGroup;
	}

	public void setLearningGroup(LearningGroup learningGroup) {
		this.learningGroup = learningGroup;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public List<Topic> getTopics() {
		return topics;
	}

	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}

	public List<Practice> getPractices() {
		return practices;
	}

	public void setPractices(List<Practice> practices) {
		this.practices = practices;
	}

}

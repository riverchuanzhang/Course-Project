/**
 * 
 */
package com.example.learning_platform.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.util.Assert;

import com.example.learning_platform.AppConstant;
import com.example.learning_platform.orm.Page;
import com.example.learning_platform.orm.PropertyFilter;
import com.example.learning_platform.pojo.LearningGroup;
import com.example.learning_platform.pojo.Paper;
import com.example.learning_platform.pojo.Practice;
import com.example.learning_platform.pojo.User;
import com.example.learning_platform.service.PaperManager;
import com.example.learning_platform.service.PracticeManager;
import com.opensymphony.xwork2.ActionSupport;


/**
 * @author zdk
 * 
 */
public class PracticeAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8506302140281213353L;

	private PracticeManager practiceManager;
	private Paper paper;
	private User user;
	private LearningGroup learningGroup;
	private PaperManager paperManager;
	private Page<Practice> page = new Page<Practice>(
			AppConstant.DEFAULT_PAGE_SIZE);

	public String listForUser() throws Exception {
		Assert.notNull(user);
		HttpServletRequest request = ServletActionContext.getRequest();
		List<PropertyFilter> filters = PropertyFilter
				.buildFromHttpRequest(request);
		if (!page.isOrderBySetted()) {// 设置默认排序方式
			page.setOrderBy("type");
			page.setOrder(Page.ASC);
		}
		filters.add(new PropertyFilter("EQL_creater.id", user.getId() + ""));
		page = practiceManager.findPage(page, filters);
		return "user-practice";
	}

	public PracticeManager getPracticeManager() {
		return practiceManager;
	}

	public void setPracticeManager(PracticeManager practiceManager) {
		this.practiceManager = practiceManager;
	}

	public Paper getPaper() {
		return paper;
	}

	public void setPaper(Paper paper) {
		this.paper = paper;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LearningGroup getLearningGroup() {
		return learningGroup;
	}

	public void setLearningGroup(LearningGroup learningGroup) {
		this.learningGroup = learningGroup;
	}

	public PaperManager getPaperManager() {
		return paperManager;
	}

	public void setPaperManager(PaperManager paperManager) {
		this.paperManager = paperManager;
	}

	public Page<Practice> getPage() {
		return page;
	}

	public void setPage(Page<Practice> page) {
		this.page = page;
	}

}

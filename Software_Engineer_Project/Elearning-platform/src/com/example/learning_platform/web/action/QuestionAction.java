/**
 * 
 */
package com.example.learning_platform.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.example.learning_platform.AppConstant;
import com.example.learning_platform.orm.Page;
import com.example.learning_platform.orm.PropertyFilter;
import com.example.learning_platform.pojo.LearningGroup;
import com.example.learning_platform.pojo.Question;
import com.example.learning_platform.pojo.User;
import com.example.learning_platform.service.LearningGroupManager;
import com.example.learning_platform.service.QuestionManager;
import com.opensymphony.xwork2.ActionSupport;


/**
 * @author zdk
 * 
 */
public class QuestionAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2315220659500670322L;

	private Question question;
	private LearningGroup learningGroup;
	private LearningGroupManager learningGroupManager;

	private QuestionManager questionManager;

	private Page<Question> requestPage = new Page<Question>(
			AppConstant.DEFAULT_PAGE_SIZE);

	private Page<Question> responsePage = new Page<Question>(
			AppConstant.DEFAULT_PAGE_SIZE);
	private Page<Question> page = new Page<Question>(
			AppConstant.DEFAULT_PAGE_SIZE);

	public String list() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		List<PropertyFilter> filters = PropertyFilter
				.buildFromHttpRequest(request);
		if (!page.isOrderBySetted()) {// 设置默认排序方式
			page.setOrderBy("title");
			page.setOrder(Page.ASC);
		}

		page = questionManager.findPage(page, filters);
		return "list";
	}

	public String listForUser() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			return "index";
		}
		// 问题提出者的
		if (!requestPage.isOrderBySetted()) {// 设置默认排序方式
			requestPage.setOrderBy("title");
			requestPage.setOrder(Page.ASC);
		}
		List<PropertyFilter> requestFilters = new ArrayList<PropertyFilter>();
		requestFilters.add(new PropertyFilter("EQL_creater.id", user.getId()
				+ ""));
		requestPage = questionManager.findPage(requestPage, requestFilters);

		// 问题解决者的
		if (!responsePage.isOrderBySetted()) {// 设置默认排序方式
			responsePage.setOrderBy("title");
			responsePage.setOrder(Page.ASC);
		}
		List<PropertyFilter> responseFilters = new ArrayList<PropertyFilter>();
		responseFilters.add(new PropertyFilter("EQL_answers_creater.id", user
				.getId() + ""));
		responsePage = questionManager.findPage(responsePage, responseFilters);
		return "user-discuss";
	}

	/**
	 * 
	 * 对某个群组,进行查询问题分页
	 * 
	 * @return
	 * @throws Exception
	 */
	public String listForLGroup() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		List<PropertyFilter> filters = PropertyFilter
				.buildFromHttpRequest(request);
		if (!page.isOrderBySetted()) {// 设置默认排序方式
			page.setOrderBy("title");
			page.setOrder(Page.ASC);
		}
		learningGroup = learningGroupManager.get(learningGroup.getId());
		filters.add(new PropertyFilter("EQL_learningGroup.id", learningGroup
				.getId() + ""));
		page = questionManager.findPage(page, filters);
		return "lgroup-question_list";
	}

	/**
	 * 
	 * 添加问题
	 * 
	 * @return
	 * @throws Exception
	 */
	public String inputForLGroup() throws Exception {
		learningGroup = question.getLearningGroup();
		questionManager.save(question);
		return listForLGroup();
	}

	public String deleteForLGroup() throws Exception {
		questionManager.delete(question.getId());
		return listForLGroup();
	}

	/**
	 * 
	 * 添加问题
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toInput() throws Exception {// 作为中间桥梁,传递learningGroup.id到编辑页面
		return "lgroup-question_edit";
	}

	/**
	 * 删除
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deleteForUser() throws Exception {
		questionManager.delete(question.getId());
		return listForUser();
	}

	public String test() throws Exception {
		return null;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public QuestionManager getQuestionManager() {
		return questionManager;
	}

	public void setQuestionManager(QuestionManager questionManager) {
		this.questionManager = questionManager;
	}

	public void setLearningGroup(LearningGroup learningGroup) {
		this.learningGroup = learningGroup;
	}

	public Page<Question> getPage() {
		return page;
	}

	public void setPage(Page<Question> page) {
		this.page = page;
	}

	public LearningGroup getLearningGroup() {
		return learningGroup;
	}

	public void setLearningGroupManager(
			LearningGroupManager learningGroupManager) {
		this.learningGroupManager = learningGroupManager;
	}

	public Page<Question> getRequestPage() {
		return requestPage;
	}

	public void setRequestPage(Page<Question> requestPage) {
		this.requestPage = requestPage;
	}

	public Page<Question> getResponsePage() {
		return responsePage;
	}

	public void setResponsePage(Page<Question> responsePage) {
		this.responsePage = responsePage;
	}

}

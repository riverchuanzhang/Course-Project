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
import com.example.learning_platform.pojo.Answer;
import com.example.learning_platform.pojo.LearningGroup;
import com.example.learning_platform.pojo.Question;
import com.example.learning_platform.service.AnswerManager;
import com.example.learning_platform.service.QuestionManager;
import com.opensymphony.xwork2.ActionSupport;


/**
 * @author zdk
 * 
 */
public class AnswerAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8929951826934591418L;

	private Page<Answer> page = new Page<Answer>(AppConstant.DEFAULT_PAGE_SIZE);
	private Answer answer;
	private Question question;
	private AnswerManager answerManager;
	private QuestionManager questionManager;
	private LearningGroup learningGroup;

	/**
	 * 对于某个问题,进行答案查询分页
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		Assert.notNull(question);
		HttpServletRequest request = ServletActionContext.getRequest();
		List<PropertyFilter> filters = PropertyFilter
				.buildFromHttpRequest(request);
		if (!page.isOrderBySetted()) {// 设置默认排序方式
			page.setOrderBy("createTime");
			page.setOrder(Page.ASC);
		}
		question = questionManager.get(question.getId());// 答案对应的问题
		learningGroup = question.getLearningGroup();

		page = answerManager.findPage(page, filters, question.getId());
		return "lgroup-discuss_answer";
	}

	/**
	 * 对于某个问题,进行答案查询分页
	 * 
	 * @return
	 * @throws Exception
	 */
	public String listForLGroup() throws Exception {
		Assert.notNull(question);
		HttpServletRequest request = ServletActionContext.getRequest();
		List<PropertyFilter> filters = PropertyFilter
				.buildFromHttpRequest(request);
		if (!page.isOrderBySetted()) {// 设置默认排序方式
			page.setOrderBy("createTime");
			page.setOrder(Page.ASC);
		}
		question = questionManager.get(question.getId());// 答案对应的问题

		learningGroup = question.getLearningGroup();

		filters.add(new PropertyFilter("EQL_question.id", question.getId() + ""));
		page = answerManager.findPage(page, filters);
		return "lgroup-answer";
	}
	
	/**
	 * 保存或修改答案
	 * 
	 * @return
	 * @throws Exception
	 */
	public String inputForLGroup() throws Exception {
		question = answer.getQuestion();// 答案对应的问题
		answerManager.save(answer);
		return listForLGroup();
	}

	/**
	 * 保存或修改答案
	 * 
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		question = answer.getQuestion();// 答案对应的问题
		answerManager.save(answer);
		return list();
	}

	public String deleteForUser() throws Exception {
		answerManager.delete(answer);
		return "user-discuss";
	}

	public Page<Answer> getPage() {
		return page;
	}

	public void setPage(Page<Answer> page) {
		this.page = page;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public AnswerManager getAnswerManager() {
		return answerManager;
	}

	public void setAnswerManager(AnswerManager answerManager) {
		this.answerManager = answerManager;
	}

	public QuestionManager getQuestionManager() {
		return questionManager;
	}

	public void setQuestionManager(QuestionManager questionManager) {
		this.questionManager = questionManager;
	}

	public LearningGroup getLearningGroup() {
		return learningGroup;
	}

	public void setLearningGroup(LearningGroup learningGroup) {
		this.learningGroup = learningGroup;
	}

}

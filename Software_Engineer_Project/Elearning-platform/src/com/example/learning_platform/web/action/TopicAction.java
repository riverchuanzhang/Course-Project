/**
 * 
 */
package com.example.learning_platform.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;
import org.springframework.util.Assert;

import com.example.learning_platform.AppConstant;
import com.example.learning_platform.orm.Page;
import com.example.learning_platform.orm.PropertyFilter;
import com.example.learning_platform.pojo.LearningGroup;
import com.example.learning_platform.pojo.Paper;
import com.example.learning_platform.pojo.Topic;
import com.example.learning_platform.pojo.User;
import com.example.learning_platform.service.LearningGroupManager;
import com.example.learning_platform.service.PaperManager;
import com.example.learning_platform.service.TopicManager;
import com.example.learning_platform.utils.web.Struts2Utils;
import com.opensymphony.xwork2.ActionSupport;


/**
 * @author zdk
 * 
 */
public class TopicAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3897760013752614985L;

	private Page<Topic> page = new Page<Topic>(AppConstant.DEFAULT_PAGE_SIZE);
	private Paper paper;
	private Topic topic;
	private LearningGroup learningGroup;
	private TopicManager topicManager;
	private PaperManager paperManager;
	private LearningGroupManager learningGroupManager;

	/**
	 * 对于某个问题,进行答案查询分页
	 * 
	 * @return
	 * @throws Exception
	 */
	public String listForPaper() throws Exception {
		Assert.notNull(paper);
		HttpServletRequest request = ServletActionContext.getRequest();
		List<PropertyFilter> filters = PropertyFilter
				.buildFromHttpRequest(request);
		if (!page.isOrderBySetted()) {// 设置默认排序方式
			page.setOrderBy("type");
			page.setOrder(Page.ASC);
		}
		paper = paperManager.get(paper.getId());// 答案对应的问题

		learningGroup = paper.getLearningGroup();

		page = topicManager.findPage(page, filters, paper.getId());
		return "lgroup-paper_topic";
	}

	/**
	 * 保存或修改
	 * 
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		paper = topic.getPaper();
		Assert.notNull(paper);
		topicManager.save(topic);
		return listForPaper();
	}

	/**
	 * 编辑试卷
	 * 
	 * @return
	 * @throws Exception
	 */
	public String editForPaper() throws Exception {
		Assert.notNull(paper);
		paper = paperManager.get(paper.getId());// 答案对应的问题

		learningGroup = paper.getLearningGroup();
		return "lgroup-topic_edit";
	}

	/**
	 * 去编辑试卷包括题目
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toInput() throws Exception {
		Assert.notNull(learningGroup);
		learningGroup = learningGroupManager.get(learningGroup.getId());
		return "lgroup-topic_edit";
	}

	public String ajaxInput() throws Exception {
		JSONObject jObject = new JSONObject();
		String topicIdStr = Struts2Utils.getParameter("topic_id");
		String paperIdStr = Struts2Utils.getParameter("paper_id");
		String topicTitle = Struts2Utils.getParameter("topic_title");
		String topicDetail = Struts2Utils.getParameter("topic_detail");
		String topicScoreStr = Struts2Utils.getParameter("topic_score");
		String topicAnswer = Struts2Utils.getParameter("topic_answer");
		if (StringUtils.isEmpty(paperIdStr)) {
			jObject.put("status", false);
			jObject.put("prompt", "获取不到试卷信息");
			Struts2Utils.renderText(jObject.toString());
			return null;
		}
		if (StringUtils.isEmpty(topicDetail)) {
			jObject.put("status", false);
			jObject.put("prompt", "内容不能为空");
			Struts2Utils.renderText(jObject.toString());
			return null;
		}
		if (StringUtils.isEmpty(topicTitle)) {
			jObject.put("status", false);
			jObject.put("prompt", "标题不能为空");
			Struts2Utils.renderText(jObject.toString());
			return null;
		}
		if (StringUtils.isEmpty(topicScoreStr)) {
			jObject.put("status", false);
			jObject.put("prompt", "分数不能为空");
			Struts2Utils.renderText(jObject.toString());
			return null;
		}
		if (StringUtils.isEmpty(topicAnswer)) {
			jObject.put("status", false);
			jObject.put("prompt", "答案不能为空");
			Struts2Utils.renderText(jObject.toString());
			return null;
		}
		Paper paper = null;
		try {
			Long paperId = Long.parseLong(paperIdStr);
			paper = paperManager.get(paperId);
		} catch (Exception e) {
			e.printStackTrace();
			jObject.put("status", false);
			jObject.put("prompt", "查找所属试卷失败");
			Struts2Utils.renderText(jObject.toString());
			return null;
		}
		if (paper == null) {
			jObject.put("status", false);
			jObject.put("prompt", "无法找到所属试卷");
			Struts2Utils.renderText(jObject.toString());
			return null;
		}
		Topic topic = new Topic();
		Long topicId = null;
		try {
			if (StringUtils.isNotEmpty(topicIdStr)) {
				topicId = Long.parseLong(topicIdStr);
				topic.setId(topicId);
			}
		} catch (NumberFormatException e) {
			jObject.put("status", false);
			jObject.put("prompt", "习题的信息有误,无法修改");
			Struts2Utils.renderText(jObject.toString());
			return null;
		}

		try {
			int topicScore = Integer.parseInt(topicScoreStr);
			topic.setPaper(paper);
			topic.setTitle(topicTitle);
			topic.setDetail(topicDetail);
			topic.setScore(topicScore);
			topic.setAnswer(topicAnswer);
			topic.setType(0);
			topicManager.save(topic);
			jObject.put("status", true);
			jObject.put("prompt", "编辑成功");
			jObject.put("topic_id", topic.getId());
		} catch (NumberFormatException e1) {
			jObject.put("status", false);
			jObject.put("prompt", "试卷信息有误");

		} catch (Exception e2) {
			jObject.put("status", false);
			jObject.put("prompt", "编辑题目失败");
		}
		Struts2Utils.renderText(jObject.toString());
		return null;
	}

	public Page<Topic> getPage() {
		return page;
	}

	public String toPractice() throws Exception {
		Assert.notNull(paper);
		HttpServletRequest request = ServletActionContext.getRequest();
		List<PropertyFilter> filters = PropertyFilter
				.buildFromHttpRequest(request);
		if (!page.isOrderBySetted()) {// 设置默认排序方式
			page.setOrderBy("title");
			page.setOrder(Page.ASC);
		}
		paper = paperManager.get(paper.getId());// 答案对应的问题

		learningGroup = paper.getLearningGroup();

		filters.add(new PropertyFilter("EQL_paper.id", paper.getId() + ""));
		page = topicManager.findPage(page, filters);
		return "lgroup-practice";
	}

	public void setPage(Page<Topic> page) {
		this.page = page;
	}

	public Paper getPaper() {
		return paper;
	}

	public void setPaper(Paper paper) {
		this.paper = paper;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public LearningGroup getLearningGroup() {
		return learningGroup;
	}

	public void setLearningGroup(LearningGroup learningGroup) {
		this.learningGroup = learningGroup;
	}

	public TopicManager getTopicManager() {
		return topicManager;
	}

	public void setTopicManager(TopicManager topicManager) {
		this.topicManager = topicManager;
	}

	public PaperManager getPaperManager() {
		return paperManager;
	}

	public void setPaperManager(PaperManager paperManager) {
		this.paperManager = paperManager;
	}

	public void setLearningGroupManager(
			LearningGroupManager learningGroupManager) {
		this.learningGroupManager = learningGroupManager;
	}

}

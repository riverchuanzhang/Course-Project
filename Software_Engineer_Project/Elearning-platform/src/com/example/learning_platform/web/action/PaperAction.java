/**
 * 
 */
package com.example.learning_platform.web.action;

import java.util.ArrayList;
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
import com.example.learning_platform.pojo.Practice;
import com.example.learning_platform.pojo.User;
import com.example.learning_platform.service.LearningGroupManager;
import com.example.learning_platform.service.PaperManager;
import com.example.learning_platform.service.PracticeManager;
import com.example.learning_platform.utils.web.Struts2Utils;
import com.opensymphony.xwork2.ActionSupport;


/**
 * @author zdk
 * 
 */
public class PaperAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3516823792684881743L;

	private Paper paper;
	private LearningGroup learningGroup;
	private LearningGroupManager learningGroupManager;

	private PaperManager paperManager;

	private Practice practice;

	private PracticeManager practiceManager;

	private Page<Paper> page = new Page<Paper>(AppConstant.DEFAULT_PAGE_SIZE);

	private Page<Paper> releasedPage = new Page<Paper>(
			AppConstant.DEFAULT_PAGE_SIZE);
	private Page<Paper> completePage = new Page<Paper>(
			AppConstant.DEFAULT_PAGE_SIZE);

	/**
	 * 
	 * 对某个群组,进行查询试卷分页
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
		page = paperManager.findPage(page, filters);
		return "lgroup-paper_list";
	}

	public String listForUser() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			return "index";
		}
		List<PropertyFilter> releasedfilters = new ArrayList<PropertyFilter>();
		if (!releasedPage.isOrderBySetted()) {// 设置默认排序方式
			releasedPage.setOrderBy("title");
			releasedPage.setOrder(Page.ASC);
		}
		// 自己提交的
		releasedfilters.add(new PropertyFilter("EQL_creater.id", user.getId()
				+ ""));
		releasedPage = paperManager.findPage(releasedPage, releasedfilters);

		// 完成的
		List<PropertyFilter> completefilters = new ArrayList<PropertyFilter>();
		if (!completePage.isOrderBySetted()) {// 设置默认排序方式
			completePage.setOrderBy("title");
			completePage.setOrder(Page.ASC);
		}
		completefilters.add(new PropertyFilter("EQL_practices_creater.id", user
				.getId() + ""));
		completePage = paperManager.findPage(completePage, completefilters);
		return "user-practice";
	}

	/**
	 * 
	 * 添加试卷
	 * 
	 * @return
	 * @throws Exception
	 */
	public String inputForLGroup() throws Exception {
		learningGroup = paper.getLearningGroup();
		paperManager.save(paper);
		return listForLGroup();
	}

	public String ajaxInput() throws Exception {
		JSONObject jObject = new JSONObject();
		Paper paper = new Paper();
		String createrIdStr = Struts2Utils.getParameter("creater_id");
		String learningGroupIdStr = Struts2Utils
				.getParameter("learningGroup_id");
		String paperIdStr = Struts2Utils.getParameter("paper_id");
		String paperTitle = Struts2Utils.getParameter("paper_title");
		String paperIntro = Struts2Utils.getParameter("paper_intro");
		if (StringUtils.isNotEmpty(paperIdStr)) {
			try {
				Long paperId = Long.parseLong(paperIdStr);
				paper.setId(paperId);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				jObject.put("status", false);
				jObject.put("prompt", "试卷信息有误,修改失败");
				Struts2Utils.renderText(jObject.toString());
				return null;
			}
		}
		if (StringUtils.isEmpty(paperTitle)) {
			jObject.put("status", false);
			jObject.put("prompt", "标题不能为空");
			Struts2Utils.renderText(jObject.toString());
			return null;
		}
		if (StringUtils.isEmpty(paperIntro)) {
			jObject.put("status", false);
			jObject.put("prompt", "简介不能为空");
			Struts2Utils.renderText(jObject.toString());
			return null;
		}
		if (StringUtils.isEmpty(createrIdStr)
				|| StringUtils.isEmpty(learningGroupIdStr)) {
			jObject.put("status", false);
			jObject.put("prompt", "获取不到作者和群组信息");
			Struts2Utils.renderText(jObject.toString());
			return null;
		}
		try {
			Long learningGroupId = Long.parseLong(learningGroupIdStr);
			Long craterId = Long.parseLong(createrIdStr);
			User creater = new User();
			LearningGroup learningGroup = new LearningGroup();
			creater.setId(craterId);
			learningGroup.setId(learningGroupId);
			paper.setTitle(paperTitle);
			paper.setIntro(paperIntro);
			paper.setCreater(creater);
			paper.setLearningGroup(learningGroup);
			paperManager.save(paper);
			jObject.put("status", true);
			jObject.put("prompt", "编辑成功");
			jObject.put("paper_id", paper.getId());
		} catch (NumberFormatException e1) {
			jObject.put("status", false);
			jObject.put("prompt", "作者和群组信息有误");

		} catch (Exception e2) {
			jObject.put("status", false);
			jObject.put("prompt", "编辑试卷失败");
		}
		Struts2Utils.renderText(jObject.toString());
		return null;
	}

	public String savePractice() throws Exception {
		Assert.notNull(practice);
		Assert.notNull(practice.getPaper());
		practiceManager.save(practice);
		return listForLGroup();
	}

	/**
	 * 
	 * 删除试卷
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deleteForLGroup() throws Exception {
		paperManager.delete(paper.getId());
		return listForLGroup();
	}

	/**
	 * 删除试卷
	 */
	public String deleteForUser() throws Exception {
		paperManager.delete(paper.getId());
		return listForUser();
	}

	public Paper getPaper() {
		return paper;
	}

	public void setPaper(Paper paper) {
		this.paper = paper;
	}

	public LearningGroup getLearningGroup() {
		return learningGroup;
	}

	public void setLearningGroup(LearningGroup learningGroup) {
		this.learningGroup = learningGroup;
	}

	public LearningGroupManager getLearningGroupManager() {
		return learningGroupManager;
	}

	public void setLearningGroupManager(
			LearningGroupManager learningGroupManager) {
		this.learningGroupManager = learningGroupManager;
	}

	public PaperManager getPaperManager() {
		return paperManager;
	}

	public void setPaperManager(PaperManager paperManager) {
		this.paperManager = paperManager;
	}

	public Page<Paper> getPage() {
		return page;
	}

	public void setPage(Page<Paper> page) {
		this.page = page;
	}

	public Page<Paper> getReleasedPage() {
		return releasedPage;
	}

	public Page<Paper> getCompletePage() {
		return completePage;
	}

	public Practice getPractice() {
		return practice;
	}

	public void setPractice(Practice practice) {
		this.practice = practice;
	}

	public void setPracticeManager(PracticeManager practiceManager) {
		this.practiceManager = practiceManager;
	}

}

/**
 * 
 */
package com.example.learning_platform.web.action;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.util.Assert;

import com.example.learning_platform.AppConstant;
import com.example.learning_platform.orm.Page;
import com.example.learning_platform.orm.PropertyFilter;
import com.example.learning_platform.pojo.LearningGroup;
import com.example.learning_platform.pojo.User;
import com.example.learning_platform.service.LearningGroupManager;
import com.example.learning_platform.service.UserManager;
import com.opensymphony.xwork2.ActionSupport;


/**
 * @author zdk
 * 
 */
public class LearningGroupAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1367481665666201769L;

	// private Long id;

	// private Long memberId;

	private LearningGroup learningGroup;

	private LearningGroupManager learningGroupManager;

	private User member;

	private Set<User> members;

	private UserManager userManager;

	private Page<LearningGroup> page = new Page<LearningGroup>(
			AppConstant.DEFAULT_PAGE_SIZE);

	/**
	 * 群组主页
	 */
	public String home() throws Exception {
		learningGroup = learningGroupManager.get(learningGroup.getId());
		return "home";
	}

	/**
	 * 群组信息(简介)
	 */
	public String info() throws Exception {
		learningGroup = learningGroupManager.get(learningGroup.getId());
		return "lgroup-info";
	}

	/**
	 * 给用户查看所属的群组
	 */
	public String listForSearch() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		List<PropertyFilter> filters = PropertyFilter
				.buildFromHttpRequest(request);
		if (!page.isOrderBySetted()) {// 设置默认排序方式
			page.setOrderBy("name");
			page.setOrder(Page.ASC);
		}
		page = learningGroupManager.findPage(page, filters);
		return "user-search_lgroup";
	}

	/**
	 * 给用户查看所属的群组
	 */
	public String listForUser() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		List<PropertyFilter> filters = PropertyFilter
				.buildFromHttpRequest(request);
		if (!page.isOrderBySetted()) {// 设置默认排序方式
			page.setOrderBy("name");
			page.setOrder(Page.ASC);
		}
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			return "index";
		}
		filters.add(new PropertyFilter("EQL_users.id", user.getId() + ""));
		page = learningGroupManager.findPage(page, filters);
		return "user-lgroup";
	}

	/**
	 * 列出群组的成员
	 */
	public String listMember() throws Exception {
		learningGroup = learningGroupManager.get(learningGroup.getId());
		members = learningGroup.getUsers();
		return "lgroup-member";
	}

	/**
	 * 对某个组添加成员
	 * 
	 * @return
	 * @throws Exception
	 */
	public String inputMember() throws Exception {
		member = userManager.get(member.getUserName());
		learningGroupManager.addMember(learningGroup.getId(), member.getId());
		return listMember();
	}

	public String deleteMember() throws Exception {
		Assert.notNull(learningGroup);
		Assert.notNull(member);
		learningGroup = learningGroupManager.get(learningGroup.getId());
		if (member.getId().equals(learningGroup.getCreater().getId())) {
			System.out.println("不能删除创建者");
			return "error";
		}
		Set<User> users = learningGroup.getUsers();
		for (User user : users) {
			if (user.getId().equals(member.getId())) {
				users.remove(user);
				break;
			}
		}
		learningGroupManager.save(learningGroup);
		return listMember();
	}

	/**
	 * 离开群组
	 * 
	 * @return
	 * @throws Exception
	 */
	public String leave() throws Exception {
		learningGroupManager
				.deleteMember(learningGroup.getId(), member.getId());
		return listForUser();
	}

	/**
	 * 加入组的操作
	 * 
	 * @return
	 * @throws Exception
	 */
	public String enter() throws Exception {
		member = userManager.get(member.getUserName());
		learningGroupManager.addMember(learningGroup.getId(), member.getId());
		return listForUser();
	}

	/**
	 * 添加或修改一个组的
	 * 
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		// 名字不能为空
		if (StringUtils.isEmpty(learningGroup.getName())) {
			return ERROR;
		}
		learningGroupManager.save(learningGroup);
		return listForUser();
	}

	/**
	 * 删掉一个组
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		learningGroupManager.delete(learningGroup.getId());
		return listForUser();
	}

	/************************************************************/

	public String manageForMember() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		List<PropertyFilter> filters = PropertyFilter
				.buildFromHttpRequest(request);
		if (!page.isOrderBySetted()) {// 设置默认排序方式
			page.setOrderBy("name");
			page.setOrder(Page.ASC);
		}
		page = learningGroupManager.findPage(page, filters, member.getId());
		return "manage_for_member";
	}

	public LearningGroupManager getLearningGroupManager() {
		return learningGroupManager;
	}

	public void setLearningGroupManager(
			LearningGroupManager learningGroupManager) {
		this.learningGroupManager = learningGroupManager;
	}

	public Page<LearningGroup> getPage() {
		return page;
	}

	public void setPage(Page<LearningGroup> page) {
		this.page = page;
	}

	public LearningGroup getLearningGroup() {
		return learningGroup;
	}

	public void setLearningGroup(LearningGroup learningGroup) {
		this.learningGroup = learningGroup;
	}

	/*
	 * public Long getId() { return id; }
	 * 
	 * public void setId(Long id) { this.id = id; }
	 */

	public Set<User> getMembers() {
		return members;
	}

	public void setMembers(Set<User> members) {
		this.members = members;
	}

	/*
	 * public Long getMemberId() { return memberId; }
	 * 
	 * public void setMemberId(Long memberId) { this.memberId = memberId; }
	 */

	public User getMember() {
		return member;
	}

	public void setMember(User member) {
		this.member = member;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

}

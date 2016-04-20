package com.example.learning_platform.web.action;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.example.learning_platform.dao.LearningGroupDao2;
import com.example.learning_platform.pojo.LearningGroup;
import com.example.learning_platform.pojo.User;
import com.example.learning_platform.service.UserManager;
import com.opensymphony.xwork2.ActionSupport;


public class TestAction extends ActionSupport {
	private static final long serialVersionUID = -4845587985491095726L;

	private UserManager userManager;
	private ApplicationContext ctx;

	// 编程式事务
	private TransactionDefinition transactionDefinition;
	private PlatformTransactionManager transactionManager;

	public TestAction() {
		super();
		try {
			doAddition();
			System.out.println("TestAction()");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String execute() throws Exception {
		save();
		System.out.println("TestAction");
		return SUCCESS;
	}

	private void save() {
		TransactionStatus status=transactionManager.getTransaction(transactionDefinition);
		try {
			User user = new User();
			user.setUserName("name");
			user.setSex(1);
			user.setEmail("email");
			user.setRegisterTime("2012");
			userManager.save(user);
			transactionManager.commit(status);
			System.out.println("save()");
		} catch (Exception e) {
			transactionManager.rollback(status);
			e.printStackTrace();
		}

	}

	private void test()  {
		userManager.test();
		try {
			String displayName = ctx.getDisplayName();
			System.out.println(displayName);
			LearningGroupDao2 learningGroupDao = (LearningGroupDao2) ctx
					.getBean("learningGroupDao2");
			LearningGroup group = new LearningGroup();
			group.setIntro("ssfsf");
			group.setName("group1");
			learningGroupDao.save(group);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doAddition() {
		ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(ServletActionContext
						.getServletContext());
		userManager = (UserManager) ctx.getBean("userManager");
		transactionDefinition = (TransactionDefinition) ctx.getBean("transactionDefinition");
		transactionManager = (PlatformTransactionManager) ctx.getBean("transactionManager");

	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public void setTransactionDefinition(TransactionDefinition transactionDefinition) {
		this.transactionDefinition = transactionDefinition;
	}

	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

}

/**
 * 
 */
package com.example.learning_platform.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.learning_platform.dao.LearningGroupDao;
import com.example.learning_platform.dao.QuestionDao;
import com.example.learning_platform.exception.AppException;
import com.example.learning_platform.pojo.LearningGroup;
import com.example.learning_platform.pojo.Question;
import com.example.learning_platform.pojo.User;
import com.example.learning_platform.service.LearningGroupManager;


/**
 * @author zdk
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:applicationContext.xml",
		"classpath:da/kang/learning_platform/web/action/applicationContext-action.xml",
		"classpath:da/kang/learning_platform/dao/applicationContext-dao.xml",
		"classpath:da/kang/learning_platform/service/applicationContext-service.xml" })
public class LearningGroupManagerTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private LearningGroupManager learningGroupManager;

	@Autowired
	private LearningGroupDao learningGroupDao;

	@Autowired
	private QuestionDao questionDao;
	

	LearningGroup learningGroup = null;

	public void testSave() throws AppException {
		LearningGroup learningGroup = new LearningGroup();
		learningGroup.setName("test_1");
		User user = new User();
		user.setId(new Long(9));
		learningGroup.setCreater(user);
		learningGroupManager.save(learningGroup);
	}

	@Test
	@Rollback(false)
	public void testAddQuestion() throws AppException {
		init();
	/*	Question question = new Question();
		question.setTitle("sfsf");
		User creater = new User();
		creater.setId(new Long(2));
		question.setCreater(creater);
		
		learningGroupManager.addQuestion(learningGroup.getId(), question);*/
	}

	public void init() {
		learningGroup = new LearningGroup();
		learningGroup.setName("testAddQuestion");
		learningGroupDao.save(learningGroup);
	}

}

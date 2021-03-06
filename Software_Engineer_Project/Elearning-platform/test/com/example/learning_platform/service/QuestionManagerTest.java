/**
 * 
 */
package com.example.learning_platform.service;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.learning_platform.exception.AppException;
import com.example.learning_platform.pojo.Question;
import com.example.learning_platform.pojo.User;
import com.example.learning_platform.service.QuestionManager;


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
public class QuestionManagerTest extends
		AbstractTransactionalJUnit4SpringContextTests {
	
	
	@Autowired
	private QuestionManager questionManager;

	
	@Test
	@Rollback(false)
	public void test() {
		System.out.println("jj");
	}
	
	
	
	public void testSave() throws AppException{
		Question question=new Question();
		question.setId(new Long(1));
		/*question.setTitle("testSave1122");
		User creater=new User();
		creater.setId(new Long(2));*/
//		question.setCreater(creater);
		questionManager.save(question);
	}
	
	
	public void testDelete() throws AppException{
		questionManager.delete(new Long(2));
	}
	
	

}

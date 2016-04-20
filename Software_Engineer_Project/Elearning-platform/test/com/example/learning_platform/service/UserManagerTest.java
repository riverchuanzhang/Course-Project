/**
 * 
 */
package com.example.learning_platform.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.learning_platform.exception.AppException;
import com.example.learning_platform.pojo.User;
import com.example.learning_platform.service.UserManager;


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
public class UserManagerTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private UserManager userManager;

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public void test() {
		// fail("Not yet implemented");
		assertEquals(10, 10);

	}

	@Test
	public void testSave() throws AppException {
		User user = new User();
		user.setUserName("testName");
		user.setEmail("testEmail");
		user.setPassword("testPassword");
		userManager.save(user);
	}

}

/**
 * 
 */
package com.example.learning_platform.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.learning_platform.dao.LearningGroupDao;
import com.example.learning_platform.pojo.LearningGroup;


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
public class LearningGroupDaoTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private LearningGroupDao learningGroupDao;

	public void test() {
		fail("Not yet implemented");
	}

	/**
	 * 测试多对多查询
	 */
	@Test
	public void testMoreToMore() {
		List<LearningGroup> groups = null;
		Criteria c = learningGroupDao.getSession().createCriteria(
				LearningGroup.class);
		groups = c.createAlias("users", "users")
				.add(Restrictions.eq("users.userName","b")).list();
		System.out.println(groups.size());

	}

}

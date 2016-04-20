/**
 * 
 */
package com.example.learning_platform.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.learning_platform.dao.LearningGroupDao;
import com.example.learning_platform.dao.PaperDao;
import com.example.learning_platform.dao.TopicDao;
import com.example.learning_platform.dao.UserDao;
import com.example.learning_platform.pojo.LearningGroup;
import com.example.learning_platform.pojo.Paper;
import com.example.learning_platform.pojo.Topic;
import com.example.learning_platform.pojo.User;


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
public class TopicDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private TopicDao topicDao;
	@Autowired
	private PaperDao paperDao;

	@Autowired
	private UserDao userDao;
	@Autowired
	private LearningGroupDao learningGroupDao;

	public void testSave() {
		for (int i = 0; i < 10; i++) {
			Paper paper = paperDao.get(new Long(1));
			Topic topic = new Topic();
			topic.setTitle("title" + i);
			topic.setScore(23);
			topic.setPaper(paper);
			topicDao.save(topic);
		}

	}

	public void testCacheDelte() {

	}

	public void testManayCacheSave() {
		// Paper paper =new Paper();
		// paper.setIntro("cache-intro");
		Paper paper = paperDao.get(new Long(2));
		Topic topic = topicDao.get(new Long(1));
		// Topic topic = new Topic();
		// topic.setTitle("title-cache");
		// topic.setScore(23);
		paper.getTopics().add(topic);
		// topic.setPaper(paper);
		paperDao.save(paper);
		// topicDao.save(topic);
	}

	public void testOneCacheSave() {
		User user = userDao.get(new Long(1));
		LearningGroup learningGroup = learningGroupDao.get(new Long(1));
		Paper paper = new Paper();
		paper.setIntro("intro");
		paper.setCreater(user);
		paper.setLearningGroup(learningGroup);

		Topic topic = new Topic();
		topic.setTitle("title-cache");
		topic.setScore(23);
		topic.setPaper(paper);
		topicDao.save(topic);
	}

	public void testSavePaper() {
		User user = new User();
		user.setUserName("uuu");
		userDao.save(user);

		Paper paper = new Paper();
		paper.setTitle("p-title");
		paper.setCreater(user);
		paperDao.save(paper);
	}

	@Rollback(false)
	@Test
	public void testDelete() {
		// Paper paper = paperDao.get(new Long(1));
		paperDao.delete(new Long(2));
		//learningGroupDao.delete(new Long(2));
	}

	public void addUser() {
		for (int i = 0; i < 6; i++) {
			User user = new User();
			user.setUserName("name" + i);
			userDao.save(user);
		}
	}

	public void addLearningGroup() {
		User user = userDao.get(new Long(1));
		for (int i = 0; i < 6; i++) {
			LearningGroup l = new LearningGroup();
			l.setIntro("intro" + i);
			l.setCreater(user);
			learningGroupDao.save(l);
		}
	}

	
	public void addPaper() {
		User user = userDao.get(new Long(1));
		LearningGroup l = learningGroupDao.get(new Long(3));
		for (int i = 0; i < 6; i++) {
			Paper p = new Paper();
			p.setIntro("intro" + i);
			p.setCreater(user);
			p.setLearningGroup(l);
			paperDao.save(p);
		}
	}
}

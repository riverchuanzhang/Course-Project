package com.example.learning_platform.dao;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.example.learning_platform.pojo.LearningGroup;


public class LearningGroupDao2 {
	final static String TAG = "LearningGroupDao";

	// 执行持久化操作
	private HibernateTemplate hibernateTemplate;
	private SessionFactory sessionFactory;
	
	public LearningGroupDao2() {
	}

	// 依赖注入
	public void setSessionFactory(SessionFactory sessionFactory) {
		System.out.println(TAG+"依赖注入");
		this.sessionFactory = sessionFactory;
	}

	public void setHibernateTemplate() {
		if (hibernateTemplate == null) {
			hibernateTemplate = new HibernateTemplate(sessionFactory);
		}
	}

	public void save(LearningGroup learningGroup) {
		System.out.println(TAG+"save");
		setHibernateTemplate();
		Long o= (Long) hibernateTemplate.save(learningGroup);
		if(o==null){
			System.out.println("null");
		}else{
			System.out.println(o+"not null");
		}
		
	}

	public void update(LearningGroup learningGroup) {
		setHibernateTemplate();
		hibernateTemplate.update(learningGroup);
	}

	public LearningGroup get(Long groupId) {
		setHibernateTemplate();
		return (LearningGroup) hibernateTemplate.get(LearningGroup.class,
				groupId);
	}

	public void delete(Long groupId) {
		setHibernateTemplate();
		hibernateTemplate.delete(hibernateTemplate.get(LearningGroup.class,
				groupId));
	}

	public void delete(LearningGroup learningGroup) {
		setHibernateTemplate();
		hibernateTemplate.delete(learningGroup);
	}
}

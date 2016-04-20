package com.example.learning_platform.service;

import java.util.List;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.util.Assert;

import com.example.learning_platform.dao.TopicDao;
import com.example.learning_platform.exception.AppException;
import com.example.learning_platform.exception.ExceptionInfo;
import com.example.learning_platform.exception.Status;
import com.example.learning_platform.orm.Page;
import com.example.learning_platform.orm.PropertyFilter;
import com.example.learning_platform.orm.TransactionSupport;
import com.example.learning_platform.pojo.Answer;
import com.example.learning_platform.pojo.Topic;


public class TopicManager implements TransactionSupport {
	private TopicDao topicDao;

	// 编程式事务
	private TransactionDefinition transactionDefinition;
	private PlatformTransactionManager transactionManager;

	/**
	 * 获取Topic
	 */
	public Topic get(Long topicId) throws AppException {
		Topic topic = null;
		TransactionStatus status = transactionManager
				.getTransaction(transactionDefinition);
		try {
			topic = topicDao.get(topicId);
			transactionManager.commit(status);
		} catch (Exception e) {
			e.printStackTrace();
			transactionManager.rollback(status);
			throw new AppException(new Status(ExceptionInfo.Type.DB_SEARCH, e));
		}
		return topic;
	}

	/**
	 * 保存或修改Topic
	 */
	public void save(Topic topic) throws AppException {
		Assert.notNull(topic.getPaper());
		TransactionStatus status = transactionManager
				.getTransaction(transactionDefinition);
		try {
			topicDao.save(topic);
			topicDao.getSession().clear();
			transactionManager.commit(status);
		} catch (Exception e) {
			e.printStackTrace();
			transactionManager.rollback(status);
			throw new AppException(new Status(ExceptionInfo.Type.DB_SAVE, e));
		}
	}

	/**
	 * 分页
	 */
	public Page<Topic> findPage(final Page<Topic> page,
			final List<PropertyFilter> filters, Long paperId)
			throws AppException {
		Assert.notNull(paperId);
		Page<Topic> result = null;
		TransactionStatus status = transactionManager
				.getTransaction(transactionDefinition);
		try {
			filters.add(new PropertyFilter("EQL_paper.id", paperId + ""));
			result = topicDao.findPage(page, filters);
			transactionManager.commit(status);
		} catch (Exception e) {
			e.printStackTrace();
			transactionManager.rollback(status);
			throw new AppException(new Status(ExceptionInfo.Type.DB_SEARCH, e));
		}
		return result;
	}

	/**
	 * 分页
	 */
	public Page<Topic> findPage(final Page<Topic> page,
			final List<PropertyFilter> filters) throws AppException {
		Page<Topic> result = null;
		TransactionStatus status = transactionManager
				.getTransaction(transactionDefinition);
		try {
			result = topicDao.findPage(page, filters);
			transactionManager.commit(status);
		} catch (Exception e) {
			e.printStackTrace();
			transactionManager.rollback(status);
			throw new AppException(new Status(ExceptionInfo.Type.DB_SEARCH, e));
		}
		return result;
	}

	public void setTopicDao(TopicDao topicDao) {
		this.topicDao = topicDao;
	}

	@Override
	public void setTransactionDefinition(
			TransactionDefinition transactionDefinition) {
		this.transactionDefinition = transactionDefinition;
	}

	@Override
	public void setTransactionManager(
			PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

}

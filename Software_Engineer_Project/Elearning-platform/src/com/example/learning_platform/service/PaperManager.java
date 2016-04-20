package com.example.learning_platform.service;

import java.util.List;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import com.example.learning_platform.dao.PaperDao;
import com.example.learning_platform.dao.TopicDao;
import com.example.learning_platform.exception.AppException;
import com.example.learning_platform.exception.ExceptionInfo;
import com.example.learning_platform.exception.Status;
import com.example.learning_platform.orm.Page;
import com.example.learning_platform.orm.PropertyFilter;
import com.example.learning_platform.orm.TransactionSupport;
import com.example.learning_platform.pojo.Paper;
import com.example.learning_platform.pojo.Question;


/**
 * @author zdc
 * 
 */
public class PaperManager implements TransactionSupport {
	private PaperDao paperDao;
	private TopicDao topicDao;

	// 编程式事务
	private TransactionDefinition transactionDefinition;
	private PlatformTransactionManager transactionManager;

	/**
	 * 对某个群组,查询试卷分页
	 */
	public Page<Paper> findPage(final Page<Paper> page,
			final List<PropertyFilter> filters, Long learningGroupId)
			throws AppException {
		Page<Paper> result = null;
		TransactionStatus status = transactionManager
				.getTransaction(transactionDefinition);
		try {
			filters.add(new PropertyFilter("EQL_learningGroup.id",
					learningGroupId + ""));
			result = paperDao.findPage(page, filters);
			transactionManager.commit(status);
		} catch (Exception e) {
			e.printStackTrace();
			transactionManager.rollback(status);
			throw new AppException(new Status(ExceptionInfo.Type.DB_SEARCH, e));
		}
		return result;
	}

	public Page<Paper> findPage(final Page<Paper> page,
			final List<PropertyFilter> filters) throws AppException {
		Page<Paper> result = null;
		TransactionStatus status = transactionManager
				.getTransaction(transactionDefinition);
		try {
			result = paperDao.findPage(page, filters);
			transactionManager.commit(status);
		} catch (Exception e) {
			e.printStackTrace();
			transactionManager.rollback(status);
			throw new AppException(new Status(ExceptionInfo.Type.DB_SEARCH, e));
		}
		return result;
	}

	/**
	 * 获取试卷
	 */
	public Paper get(Long id) throws AppException {
		Paper paper = null;
		TransactionStatus status = transactionManager
				.getTransaction(transactionDefinition);
		try {
			paper = paperDao.get(id);
			transactionManager.commit(status);
		} catch (Exception e) {
			e.printStackTrace();
			transactionManager.rollback(status);
			throw new AppException(new Status(ExceptionInfo.Type.DB_SEARCH, e));
		}
		return paper;
	}

	/**
	 * 添加试卷
	 */
	public void save(Paper paper) throws AppException {
		TransactionStatus status = transactionManager
				.getTransaction(transactionDefinition);
		try {
			paperDao.save(paper);
			paperDao.getSession().clear();
			transactionManager.commit(status);
		} catch (Exception e) {
			e.printStackTrace();
			transactionManager.rollback(status);
			throw new AppException(new Status(ExceptionInfo.Type.DB_SAVE, e));
		}
	}

	/**
	 * 删除试卷
	 */
	public void delete(Long paperId) throws AppException {
		TransactionStatus status = transactionManager
				.getTransaction(transactionDefinition);
		try {
			topicDao.batchExecute("delete from Topic where paper.id=?", paperId);
			paperDao.delete(paperId);
			transactionManager.commit(status);
		} catch (Exception e) {
			e.printStackTrace();
			transactionManager.rollback(status);
			throw new AppException(new Status(ExceptionInfo.Type.DB_DELETE, e));
		}
	}

/*	*//**
	 * 删除试卷
	 *//*
	public void delete(Paper paper) throws AppException {
		TransactionStatus status = transactionManager
				.getTransaction(transactionDefinition);
		try {
			paperDao.delete(paper);
			transactionManager.commit(status);
		} catch (Exception e) {
			e.printStackTrace();
			transactionManager.rollback(status);
			throw new AppException(new Status(ExceptionInfo.Type.DB_DELETE, e));
		}
	}*/

	public void setPaperDao(PaperDao paperDao) {
		this.paperDao = paperDao;
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

	public void setTopicDao(TopicDao topicDao) {
		this.topicDao = topicDao;
	}

}

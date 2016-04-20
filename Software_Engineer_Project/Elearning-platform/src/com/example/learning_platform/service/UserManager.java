package com.example.learning_platform.service;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import com.example.learning_platform.dao.UserDao;
import com.example.learning_platform.exception.AppException;
import com.example.learning_platform.exception.ExceptionInfo;
import com.example.learning_platform.exception.Status;
import com.example.learning_platform.orm.Page;
import com.example.learning_platform.orm.PropertyFilter;
import com.example.learning_platform.orm.TransactionSupport;
import com.example.learning_platform.pojo.LearningGroup;
import com.example.learning_platform.pojo.Question;
import com.example.learning_platform.pojo.User;


public class UserManager implements TransactionSupport {

	// 编程式事务
	private TransactionDefinition transactionDefinition;
	private PlatformTransactionManager transactionManager;
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public User get(Long id) throws AppException {
		User user = null;
		TransactionStatus status = transactionManager
				.getTransaction(transactionDefinition);
		try {
			user = userDao.get(id);
			transactionManager.commit(status);
		} catch (Exception e) {
			transactionManager.rollback(status);
			throw new AppException(new Status(ExceptionInfo.Type.DB_SEARCH, e));
		}
		return user;
	}

	public User get(String userName) throws AppException {
		User user = null;
		TransactionStatus status = transactionManager
				.getTransaction(transactionDefinition);
		try {
			Criterion criterion = Restrictions.eq("userName", userName);
			user = userDao.findUnique(criterion);
			transactionManager.commit(status);
		} catch (Exception e) {
			transactionManager.rollback(status);
			throw new AppException(new Status(ExceptionInfo.Type.DB_SEARCH, e));
		}
		return user;
	}

	public void save(User user) throws AppException {
		TransactionStatus status = transactionManager
				.getTransaction(transactionDefinition);
		try {
			userDao.save(user);
			userDao.getSession().clear();
			transactionManager.commit(status);
		} catch (Exception e) {
			transactionManager.rollback(status);
			throw new AppException(new Status(ExceptionInfo.Type.DB_SAVE, e));
		}
	}

	public User getUserByNameAndPassword(String userName, String password)
			throws AppException {
		User user = null;
		TransactionStatus status = transactionManager
				.getTransaction(transactionDefinition);
		try {
			Criterion criterion = Restrictions.eq("userName", userName);
			criterion = Restrictions.and(criterion,
					Restrictions.eq("password", password));
			user = userDao.findUnique(criterion);
			transactionManager.commit(status);
		} catch (Exception e) {
			e.printStackTrace();
			transactionManager.rollback(status);
			throw new AppException(new Status(ExceptionInfo.Type.DB_OTHERS, e));
		}
		return user;
	}

	public Page<User> findPage(final Page<User> page,
			final List<PropertyFilter> filters) throws AppException {
		Page<User> result = null;
		TransactionStatus status = transactionManager
				.getTransaction(transactionDefinition);
		try {
			result = userDao.findPage(page, filters);
			transactionManager.commit(status);
		} catch (Exception e) {
			e.printStackTrace();
			transactionManager.rollback(status);
			throw new AppException(new Status(ExceptionInfo.Type.DB_SEARCH, e));
		}
		return result;
	}

	public void test() throws RuntimeException {
		throw new RuntimeException();
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

package com.example.learning_platform.orm;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;

/**
 * 用于编程式事务
 * 
 * @author zdc
 * 
 */
public interface TransactionSupport {
	public void setTransactionDefinition(
			TransactionDefinition transactionDefinition);

	public void setTransactionManager(
			PlatformTransactionManager transactionManager);

}

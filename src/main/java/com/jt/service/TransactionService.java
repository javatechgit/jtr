package com.jt.service;

import org.springframework.validation.Errors;
import com.jt.entity.TransactionEntity;

   public interface TransactionService {
	  public Object saveTransaction(TransactionEntity transactionEntity,Errors errors); 
	  public Object getTransactionById(Long transactionId);
	  public Object getTransactionRewardsById(Long customerId);
	
}

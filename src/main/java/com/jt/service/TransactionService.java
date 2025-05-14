package com.jt.service;

<<<<<<< HEAD
=======
<<<<<<< HEAD
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;


import com.jt.entity.TransactionEntity;

public interface TransactionService {
	  TransactionEntity saveTransactionEntity(TransactionEntity transactionEntity);
	  Optional<TransactionEntity> findTransactionsById(Long customerId);
	  List<TransactionEntity> findTransactionBetweenDate(Long customerId,Timestamp startDate, Timestamp endDate);
=======
>>>>>>> feature
import org.springframework.validation.Errors;
import com.jt.entity.TransactionEntity;

   public interface TransactionService {
	  public Object saveTransaction(TransactionEntity transactionEntity,Errors errors); 
	  public Object getTransactionById(Long transactionId);
	  public Object getTransactionRewardsById(Long customerId);
	
<<<<<<< HEAD
=======
>>>>>>> 4468398 (commit changes)
>>>>>>> feature
}

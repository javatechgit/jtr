package com.jt.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;


import com.jt.entity.TransactionEntity;

public interface TransactionService {
	  TransactionEntity saveTransactionEntity(TransactionEntity transactionEntity);
	  Optional<TransactionEntity> findTransactionsById(Long customerId);
	  List<TransactionEntity> findTransactionBetweenDate(Long customerId,Timestamp startDate, Timestamp endDate);
}

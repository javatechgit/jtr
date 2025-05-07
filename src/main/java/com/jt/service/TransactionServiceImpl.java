package com.jt.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.entity.TransactionEntity;
import com.jt.repository.TransactionRepository;


@Service
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	TransactionRepository transactionRepository;
	
	 public TransactionEntity saveTransactionEntity(TransactionEntity transactionEntity) {
	        return transactionRepository.save(transactionEntity);
	    }
	 
	 public Optional<TransactionEntity> findTransactionsById(Long customerId) {
		 return transactionRepository.findById(customerId);
	 }

	public List<TransactionEntity> findTransactionBetweenDate(Long customerId,Timestamp startDate,
			Timestamp endDate) {
		return transactionRepository.findAllByCustomerIdAndTransactionDateBetween(customerId,startDate, endDate);
	}
}

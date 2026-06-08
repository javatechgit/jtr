package com.jt.service;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import com.jt.entity.TransactionEntity;
import com.jt.exception.ResourceNotFoundException;

   public interface TransactionService {
	  public ResponseEntity<?> saveTransaction(TransactionEntity transactionEntity); 
	  public ResponseEntity<?> getTransactionById(Long transactionId) throws ResourceNotFoundException;
	 // public Object getTransactionById(Long transactionId);
	 // public Object getTransactionRewardsById(Long customerId); // for Junit Object
	  public ResponseEntity<?> getTransactionRewardsById(Long customerId)/*throws ResourceNotFoundException*/;
	
}

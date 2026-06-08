package com.jt.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

import com.jt.constant.ApiConstants;
import com.jt.entity.CustomerEntity;
import com.jt.entity.TransactionEntity;
import com.jt.errors.ApiErrors;
import com.jt.exception.ResourceNotFoundException;
import com.jt.model.ApiResponse;
import com.jt.repository.CustomerRepository;
import com.jt.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Override
	@Transactional
	public ResponseEntity<?> saveTransaction(TransactionEntity transactionEntity) {	
		/* List<String> fieldErrorList = errors.getFieldErrors().stream().map(fe->fe.getDefaultMessage()).collect(Collectors.toList());
		 ApiErrors errorResponse = new ApiErrors("API_BAD_REQUEST", fieldErrorList);	
		 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);	 
		 }*/
	 try {
	 Optional<CustomerEntity> dbCustomerId = customerRepository.findById(transactionEntity.getCustomerId());
	 if(dbCustomerId.isPresent()) { 
		 return new ResponseEntity<>(transactionRepository.save(transactionEntity),HttpStatus.CREATED);
	 }else {
		 ApiErrors errorResponse = new ApiErrors("CUSTOMER_NOT_FOUND", Arrays.asList("Customer id not exist in db table.Can't create this transaction")); 
	   return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
	 }
	 }catch(Exception e) {
	   return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
	 }
			/*
			 * else { ApiErrors errorResponse = new ApiErrors("CUSTOMER_NOT_FOUND", Arrays.
			 * asList("Customer id not exist in db table.Can't create this transaction"));
			 * return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse); }
			 */
	}
	
	@Override
	@Transactional
	public ResponseEntity<?> getTransactionRewardsById(Long customerId)/* throws ResourceNotFoundException*/ {
	    List<TransactionEntity> lastMonthTransactions = new ArrayList<>();
	    List<TransactionEntity> lastSecondMonthTransactions = new ArrayList<>();
	    List<TransactionEntity> lastThirdMonthTransactions = new ArrayList<>();
	    try {
	    Optional<CustomerEntity> dbCustomerId = customerRepository.findById(customerId);
	    if(dbCustomerId.isPresent()) {
	    ApiResponse apiResponse = new ApiResponse();	  
	    apiResponse.setSuccess("Get All Transactions Successfully for Customer:");
	    apiResponse.setCustomerId(dbCustomerId.get().getCustomerId());
	    apiResponse.setCustomerFName(dbCustomerId.get().getCustomerFName());
	    apiResponse.setCustomerLName(dbCustomerId.get().getCustomerLName());
	    
		lastMonthTransactions =  transactionRepository.findAllByCustomerIdAndTransactionDateBetween(customerId, ApiConstants.LAST_MONTH_TRANSACTION_FROM_TODAY_TO_30_DAYS_BACK,  ApiConstants.FROM_TODAY_DATE);
		lastSecondMonthTransactions =  transactionRepository.findAllByCustomerIdAndTransactionDateBetween(customerId, ApiConstants.LAST_SECOND_MONTH_TRANSACTION_FROM_TODAY_TO_60_DAYS_BACK,  ApiConstants.LAST_MONTH_TRANSACTION_FROM_TODAY_TO_30_DAYS_BACK);
		lastThirdMonthTransactions =  transactionRepository.findAllByCustomerIdAndTransactionDateBetween(customerId, ApiConstants.LAST_THIRD_MONTH_TRANSACTION_FROM_TODAY_TO_90_DAYS_BACK, ApiConstants.LAST_SECOND_MONTH_TRANSACTION_FROM_TODAY_TO_60_DAYS_BACK);
		  
		double lastMonthTAmt =lastMonthTransactions.stream().map(t->t.getTransactionAmount()).collect(Collectors.summingDouble(Double::doubleValue));
		double lastSecondMonthTAmt =lastSecondMonthTransactions.stream().map(t->t.getTransactionAmount()).collect(Collectors.summingDouble(Double::doubleValue));
		double lastThirdMonthTAmt =lastThirdMonthTransactions.stream().map(t->t.getTransactionAmount()).collect(Collectors.summingDouble(Double::doubleValue));
		double totalTAmt = lastMonthTAmt + lastSecondMonthTAmt + lastThirdMonthTAmt;
		
		Long lastMonthRewardPoints =	lastMonthTransactions.stream().map(transaction -> totalRewardPoints(transaction)).collect(Collectors.summingLong(Long::longValue));
		Long lastSecondMonthRewardPoints =	lastSecondMonthTransactions.stream().map(transaction -> totalRewardPoints(transaction)).collect(Collectors.summingLong(Long::longValue));
		Long lastThirdMonthRewardPoints =	lastThirdMonthTransactions.stream().map(transaction -> totalRewardPoints(transaction)).collect(Collectors.summingLong(Long::longValue));
		Long totalRewardPoints = lastMonthRewardPoints + lastSecondMonthRewardPoints + lastThirdMonthRewardPoints;
		apiResponse.setLastMonthTransactionsAmt(lastMonthTAmt);
		apiResponse.setLastSecondMonthTransactionsAmt(lastSecondMonthTAmt);
		apiResponse.setLastThirdMonthTransactionsAmt(lastThirdMonthTAmt);
		apiResponse.setTotalTransactionsAmt(totalTAmt);
		apiResponse.setLastMonthRwdPoints(lastMonthRewardPoints);
		apiResponse.setLastSecondMonthRwdPoints(lastSecondMonthRewardPoints);
		apiResponse.setLastThirdMonthRwdPoints(lastThirdMonthRewardPoints);
		apiResponse.setTotalRewardPoints(totalRewardPoints);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
		}else {
		ApiErrors errorResponse = new ApiErrors("CUSTOMER_NOT_FOUND", Arrays.asList("Customer id not found/invalid"));	
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		}
	    }catch(Exception e) {
	    	// throw new ResourceNotFoundException(e.getLocalizedMessage());
	    	return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
	    }
	}
	
	private Long totalRewardPoints(TransactionEntity transactionEntity) {
	   long totalRewardPoints = 0;
	   double firstAmountForRwrd = 50;
	   double secondAmountForRwrd = 100;
	   if(transactionEntity.getTransactionAmount() > secondAmountForRwrd) {
	   totalRewardPoints = Math.round( (transactionEntity.getTransactionAmount()- secondAmountForRwrd)*2 + (secondAmountForRwrd-firstAmountForRwrd));
	   }else if(transactionEntity.getTransactionAmount()> firstAmountForRwrd && transactionEntity.getTransactionAmount()< secondAmountForRwrd) {
			totalRewardPoints = Math.round( transactionEntity.getTransactionAmount()- firstAmountForRwrd);
		}else {
			totalRewardPoints = 0;
		}
	   return totalRewardPoints;
	   }
	
	@Override
	@Transactional
	public ResponseEntity<?> getTransactionById(Long transactionId) throws ResourceNotFoundException {
		try {
	return new ResponseEntity<>(transactionRepository.findById(transactionId).get(),HttpStatus.OK);
	//return new ResponseEntity<>(HttpStatus.OK).body(customerRepository.findById(customerId));
	}catch(Exception e) {
	throw new ResourceNotFoundException("TransactionId:"+transactionId+" "+e.getMessage());
		//return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	}
	
	/*
	 * @Override public Object getTransactionById(Long transactionId) {
	 * if(transactionId ==null) { ApiErrors errorResponse = new
	 * ApiErrors("TRANSACTION_NOT_FOUND",
	 * Arrays.asList("Transaction id not found or invalid")); return
	 * ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse); } return
	 * ResponseEntity.status(HttpStatus.OK).body(transactionRepository.findById(
	 * transactionId));
	 * 
	 * }
	 */
}

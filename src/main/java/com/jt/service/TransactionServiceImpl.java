package com.jt.service;

<<<<<<< HEAD
=======
<<<<<<< HEAD
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.entity.TransactionEntity;
import com.jt.repository.TransactionRepository;


=======
>>>>>>> feature
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import com.jt.constant.ApiConstants;
import com.jt.entity.CustomerEntity;
import com.jt.entity.TransactionEntity;
import com.jt.errors.ApiErrors;
import com.jt.model.ApiResponse;
import com.jt.repository.CustomerRepository;
import com.jt.repository.TransactionRepository;

<<<<<<< HEAD
=======
>>>>>>> 4468398 (commit changes)
>>>>>>> feature
@Service
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	TransactionRepository transactionRepository;
	
<<<<<<< HEAD
=======
<<<<<<< HEAD
	 public TransactionEntity saveTransactionEntity(TransactionEntity transactionEntity) {
	        return transactionRepository.save(transactionEntity);
	    }
	 
	 public Optional<TransactionEntity> findTransactionsById(Long customerId) {
		 return transactionRepository.findById(customerId);
	 }

	public List<TransactionEntity> findTransactionBetweenDate(Long customerId,Timestamp startDate,
			Timestamp endDate) {
		return transactionRepository.findAllByCustomerIdAndTransactionDateBetween(customerId,startDate, endDate);
=======
>>>>>>> feature
	@Autowired
	CustomerRepository customerRepository;
	
	@Override
	public Object saveTransaction(TransactionEntity transactionEntity,Errors errors) {	
		 if(errors.hasErrors()) {
		 List<String> fieldErrorList = errors.getFieldErrors().stream().map(fe->fe.getDefaultMessage()).collect(Collectors.toList());
		 ApiErrors errorResponse = new ApiErrors("API_BAD_REQUEST", fieldErrorList);	
		 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);	 
		 }
		 
		 Optional<CustomerEntity> dbCustomerId =	customerRepository.findById(transactionEntity.getCustomerId());
		 if(dbCustomerId.isPresent()) { 
		 return ResponseEntity.status(HttpStatus.CREATED).body(transactionRepository.save(transactionEntity));
		 }else {
		 ApiErrors errorResponse = new ApiErrors("CUSTOMER_NOT_FOUND", Arrays.asList("Customer id not exist in db table.Can't create this transaction"));	
		 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		 }
	}
	
	@Override
	public Object getTransactionRewardsById(Long customerId) {
	    Optional<CustomerEntity> dbCustomerId = customerRepository.findById(customerId);
	    List<TransactionEntity> lastMonthTransactions = new ArrayList<>();
	    List<TransactionEntity> lastSecondMonthTransactions = new ArrayList<>();
	    List<TransactionEntity> lastThirdMonthTransactions = new ArrayList<>();
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
	 public Object getTransactionById(Long transactionId) {	
	 if(transactionId ==null) {
			ApiErrors errorResponse = new ApiErrors("TRANSACTION_NOT_FOUND", Arrays.asList("Transaction id not found or invalid"));	
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		}
		return ResponseEntity.status(HttpStatus.OK).body(transactionRepository.findById(transactionId));
	
<<<<<<< HEAD
=======
>>>>>>> 4468398 (commit changes)
>>>>>>> feature
	}
}

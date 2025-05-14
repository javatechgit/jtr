package com.jt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jt.entity.CustomerEntity;
import com.jt.entity.TransactionEntity;
import com.jt.service.CustomerServiceImpl;
import com.jt.service.TransactionServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/retail")
public class RetailRewardController {
	
	@Autowired
	CustomerServiceImpl customerService;  
	
	@Autowired
	TransactionServiceImpl transactionService; 
	
	@PostMapping("/customers")
	public ResponseEntity<?> createCustomer(@Valid @RequestBody CustomerEntity customerEntity, Errors errors){
		return (ResponseEntity<?>)customerService.saveCustomer(customerEntity,errors);
	}
		
	@PostMapping("/transactions")
	public ResponseEntity<?> createTransaction(@Valid @RequestBody TransactionEntity transactionEntity, Errors errors){
		return (ResponseEntity<?>)transactionService.saveTransaction(transactionEntity,errors);
		}	
	
	@GetMapping("/rewards/{customerId}")
	   public ResponseEntity<?> getAllTransactionsRewardsById(@PathVariable("customerId") Long customerId){
		return (ResponseEntity<?>)transactionService.getTransactionRewardsById(customerId);
		
	}
		
}

package com.jt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jt.entity.CustomerEntity;
import com.jt.entity.TransactionEntity;
import com.jt.exception.ResourceNotFoundException;
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
	public ResponseEntity<?> createCustomer(@Valid @RequestBody CustomerEntity customerEntity/*, Errors errors*/){
		System.out.println("POST called createCustomer ");
		return customerService.saveCustomer(customerEntity/*,errors*/);
	}
		
	@PostMapping("/transactions")
	public ResponseEntity<?> createTransaction(@Valid @RequestBody TransactionEntity transactionEntity){
		
		return transactionService.saveTransaction(transactionEntity);
		}	
	
	@GetMapping("/rewards/{customerId}")
	   public ResponseEntity<?> getAllTransactionsRewardsById(@PathVariable("customerId") Long customerId)/* throws ResourceNotFoundException*/{
		System.out.println("GET All Rewards in contrller  by customerId= "+customerId);
		return transactionService.getTransactionRewardsById(customerId);
		
	}
	
	
	@GetMapping("/customers/{customerId}")
	public ResponseEntity<?> getCustomerEntityById(@PathVariable("customerId") Long customerId) throws ResourceNotFoundException{
	System.out.println("GET in contrller  customerId= "+customerId);
			return customerService.getCustomerById(customerId);
		 
	}

	@GetMapping("/transactions/{transactionId}")
	public ResponseEntity<?> getTransactionEntityById(@PathVariable("transactionId") Long transactionId) throws ResourceNotFoundException{
	System.out.println("GET transactionId= "+transactionId);
	return transactionService.getTransactionById(transactionId);
	}
	
		
}

package com.jt.controller;

<<<<<<< HEAD
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
>>>>>>> 4468398 (commit changes)
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jt.entity.CustomerEntity;
import com.jt.entity.TransactionEntity;
<<<<<<< HEAD
import com.jt.service.CustomerService;
import com.jt.service.TransactionService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
=======
import com.jt.service.CustomerServiceImpl;
import com.jt.service.TransactionServiceImpl;

import jakarta.validation.Valid;
>>>>>>> 4468398 (commit changes)

@RestController
@RequestMapping("/api/retail")
public class RetailRewardController {
	
	@Autowired
<<<<<<< HEAD
	CustomerService customerService;
	
	@Autowired
	TransactionService transactionService;
	
	@PostMapping("/customers")
	public ResponseEntity<CustomerEntity> saveCustomer(@Valid @RequestBody CustomerEntity customerEntity){
	   return new ResponseEntity<CustomerEntity>(customerService.saveCustomerEntity(customerEntity), HttpStatus.CREATED);
	 
	}

	@PostMapping("/transactions")
	public ResponseEntity<TransactionEntity> saveTransaction(@Valid @RequestBody TransactionEntity transactionEntity){
		return new ResponseEntity<TransactionEntity>(transactionService.saveTransactionEntity(transactionEntity), HttpStatus.CREATED);
	 
	}
	
   @GetMapping("/rewards/{customerId}")
   public ResponseEntity<String> getAllTransactionsRewrdsByCustomerId(@PathVariable("customerId") @Positive Long customerId){
	  CustomerEntity cusEntity = customerService.findById(customerId).orElse(null);
	  List<TransactionEntity> lastThreeMonthsTransactions = new ArrayList<>();
	  if(cusEntity !=null) {
	// LocalDate endDate = LocalDate.now();// today
	// LocalDate startDate = LocalDate.now().minusMonths(3);// last 3 month from today
     lastThreeMonthsTransactions =  transactionService.findTransactionBetweenDate(customerId, Timestamp.valueOf(LocalDateTime.now().minusMonths(3)), Timestamp.from(Instant.now()));
	 double totalAmt =lastThreeMonthsTransactions.stream().map(t->t.getTransactionAmount()).collect(Collectors.summingDouble(Double::doubleValue));
	Long totalRewardPoints =	lastThreeMonthsTransactions.stream().map(transaction -> totalRewardPoints(transaction)).collect(Collectors.summingLong(Long::longValue));
	System.out.println("totalRewardPoints ="+totalRewardPoints);
    return new ResponseEntity<String>("Customer:"+cusEntity.getCustomerFName()+":"+customerId + " has total Rewards Points: "+totalRewardPoints+" after total transaction amounts: "+totalAmt,HttpStatus.OK);
	}else {
	throw new RuntimeException("Customer Id not present or invalid");
	 }
	
    }
   
   private Long totalRewardPoints(TransactionEntity transactionEntity) {
	   long totalRewardPoints = 0;
		double fRwrdAmt = 50;
		double sRwrdAmt = 100;
	   if(transactionEntity.getTransactionAmount() > sRwrdAmt) {
		   totalRewardPoints = Math.round( (transactionEntity.getTransactionAmount()- sRwrdAmt)*2 + (sRwrdAmt-fRwrdAmt));
			}else if(transactionEntity.getTransactionAmount()> fRwrdAmt && transactionEntity.getTransactionAmount()< sRwrdAmt) {
				totalRewardPoints = Math.round( transactionEntity.getTransactionAmount()- fRwrdAmt);
			}else {
				totalRewardPoints = 0;
			}
   return totalRewardPoints;
   }
	
=======
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
		
>>>>>>> 4468398 (commit changes)
}

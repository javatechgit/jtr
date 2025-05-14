package com.jt.test;

import org.junit.jupiter.api.Test;
<<<<<<< HEAD
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RetailRewardApiApplicationTests {

	
	
	@Test
	void contextLoads() {
	}
=======
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.jt.repository.CustomerRepository;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.jt.entity.CustomerEntity;
import com.jt.entity.TransactionEntity;
import com.jt.repository.TransactionRepository;
import com.jt.service.CustomerServiceImpl;
import com.jt.service.TransactionServiceImpl;
import static org.mockito.ArgumentMatchers.any;
import org.junit.jupiter.api.Assertions;
import java.sql.Timestamp;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
class RetailRewardApiApplicationTests {

	 @Autowired
	 private MockMvc mockMvc;
	
	 @Mock
	 private CustomerRepository customerRepository;
	
	 @InjectMocks 
	 private CustomerServiceImpl customerService;
	 
	 @Mock
	 private TransactionRepository transactionRepository;
	 
	 @InjectMocks 
	 private TransactionServiceImpl transactionService;
	 
	 
	 @Test
	 public void test_saveCustomerSuccess() {
		 CustomerEntity mockCustomer = CustomerEntity.builder()
					.customerFName("CustomerFName")
					.customerLName("CustomerLName").build();

		 Mockito.when(customerRepository.save(any(CustomerEntity.class))).thenReturn(mockCustomer);

	     CustomerEntity savedCustomer = customerRepository.save(mockCustomer);
	     Assertions.assertNotNull(savedCustomer);
	     Assertions.assertTrue(savedCustomer.getCustomerFName().equals("CustomerFName"));
	     Assertions.assertEquals(savedCustomer.getCustomerLName(),"CustomerLName");
	 }
	 
	 @Test
	 public void test_saveTransactionSuccess() {
			TransactionEntity mockTransaction = TransactionEntity.builder()
	    			.customerId(1L)
	    			.transactionDate(Timestamp.valueOf("2025-05-06 00:00:00.0"))
	    			.transactionAmount(70.0).build();

		Mockito.when(transactionRepository.save(any(TransactionEntity.class))).thenReturn(mockTransaction);

		TransactionEntity savedTransaction = transactionRepository.save(mockTransaction);
		Assertions.assertNotNull(savedTransaction);
	    Assertions.assertTrue(savedTransaction.getTransactionDate().equals(Timestamp.valueOf("2025-05-06 00:00:00.0")));
	    Assertions.assertEquals(savedTransaction.getTransactionAmount(),70.0);
	
	 }
	 
	 @Test
	 public void test_getAllTransactionsRewardsById_Endpoint() throws Exception {
	    mockMvc.perform(MockMvcRequestBuilders.get("/api/retail/rewards/1"))
	       .andExpect(MockMvcResultMatchers.status().isOk());
	  }
	 
	 @Test
	 public void test_findByCustomerId() {
		long customerId = 1L;
		CustomerEntity mockCustomer = CustomerEntity.builder()
				.customerId(customerId)
				.customerFName("CustomerFName")
				.customerLName("CustomerLName").build();
	    
		Mockito.when(customerRepository.findById(1L)).thenReturn(Optional.of(mockCustomer));
		Object result =  customerService.getCustomerById(1L);
	    Assertions.assertNotNull(result);
		
		 }
	 
	  @Test
	  public void test_findByTransactionId() {
    	long transactionId = 1L;
    	long customerId = 1L;
    	TransactionEntity mockTransaction = TransactionEntity.builder()
    			.transactionId(transactionId)
    			.customerId(customerId)
    			.transactionDate(Timestamp.valueOf("2025-05-06 00:00:00.0"))
    			.transactionAmount(70.0).build();

	     Mockito.when(transactionRepository.findById(transactionId)).thenReturn(Optional.of(mockTransaction)); 
         Object result = transactionService.getTransactionById(transactionId);	 
         Assertions.assertNotNull(result);
	 }
 
	 
	 	 
	  @Test 
	  public void test_totalRewardPoints_firstAmountForRwrd_PrivateMethod() {
	  double firstAmountForRwrdBtw50and100 = 80;
	  TransactionEntity mockTransaction = TransactionEntity.builder()
    			.transactionId(1L)
    			.customerId(1L)
    			.transactionDate(Timestamp.valueOf("2025-05-06 00:00:00.0"))
    			.transactionAmount(firstAmountForRwrdBtw50and100).build();
	
	  Long totPoints= ReflectionTestUtils.invokeMethod(transactionService,"totalRewardPoints",mockTransaction); 
	  Assertions.assertEquals(30, totPoints); 
	  }
		
		
	  @Test 
	  public void test_totalRewardPoints_secondAmountForRwrd_PrivateMethod() {
		  double secondAmountForRwrdAbv100 = 120;
		  TransactionEntity mockTransaction = TransactionEntity.builder()
				  .transactionId(1L)
				  .customerId(1L)
				  .transactionDate(Timestamp.valueOf("2025-05-06 00:00:00.0"))
				  .transactionAmount(secondAmountForRwrdAbv100).build();
		  
		  Long totPoints= ReflectionTestUtils.invokeMethod(transactionService,"totalRewardPoints",mockTransaction); 
		  Assertions.assertEquals(90, totPoints); 
	  }
	
>>>>>>> 4468398 (commit changes)

}

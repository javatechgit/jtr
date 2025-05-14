package com.jt.test;

import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.jt.entity.CustomerEntity;
import com.jt.repository.CustomerRepository;
import com.jt.service.CustomerServiceImpl;

@SpringBootTest
public class CustomerServiceLayerTest {
	
	@InjectMocks
	private CustomerServiceImpl customerService;
	
	@Mock
	private CustomerRepository customerRepository;
	
	@Test
	 public void test_saveCustomerSuccess() {
		 CustomerEntity mockCustomer = CustomerEntity.builder()
					.customerFName("CustomerFName")
					.customerLName("CustomerLName").build();

		 Mockito.when(customerRepository.save(any(CustomerEntity.class))).thenReturn(mockCustomer);

	     CustomerEntity savedCustomer = customerRepository.save(mockCustomer);
	     Assertions.assertTrue(savedCustomer.getCustomerFName().equals("CustomerFName"));
	 }
	 
	 
	 

}

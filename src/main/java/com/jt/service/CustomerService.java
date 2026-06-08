package com.jt.service;


import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import com.jt.entity.CustomerEntity;
import com.jt.exception.ResourceNotFoundException;


public interface CustomerService {
	 public ResponseEntity<CustomerEntity> saveCustomer(CustomerEntity customerEntity/*,Errors errors*/); 
	 public ResponseEntity<?> getCustomerById(Long customerId) throws ResourceNotFoundException;
	 
}

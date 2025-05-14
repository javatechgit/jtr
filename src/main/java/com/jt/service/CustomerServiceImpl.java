package com.jt.service;

<<<<<<< HEAD
=======
<<<<<<< HEAD
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.entity.CustomerEntity;
=======
>>>>>>> feature
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import com.jt.entity.CustomerEntity;
import com.jt.errors.ApiErrors;
<<<<<<< HEAD
=======
>>>>>>> 4468398 (commit changes)
>>>>>>> feature
import com.jt.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{
    
	@Autowired
	CustomerRepository customerRepository;
	
<<<<<<< HEAD
=======
<<<<<<< HEAD
	
	 public CustomerEntity saveCustomerEntity(CustomerEntity customerEntity) {
	        return customerRepository.save(customerEntity);
	    }
	 public Optional<CustomerEntity> findById(Long customerId) {
		 return customerRepository.findById(customerId);
	 }
=======
>>>>>>> feature
	@Override
	public Object saveCustomer(CustomerEntity customerEntity,Errors errors) {  
	if(errors.hasErrors()) {
	List<String> fieldErrorList = errors.getFieldErrors().stream().map(fe->fe.getDefaultMessage()).collect(Collectors.toList());
	ApiErrors errorResponse = new ApiErrors("API_BAD_REQUEST", fieldErrorList);	
	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);	
	}
	return ResponseEntity.status(HttpStatus.CREATED).body(customerRepository.save(customerEntity));
	}
	
	@Override
	public Object getCustomerById(Long customerId) {
	if(customerId ==null) {
	ApiErrors errorResponse = new ApiErrors("CUSTOMER_NOT_FOUND", Arrays.asList("Customer id not found or invalid"));	
	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}
	return ResponseEntity.status(HttpStatus.OK).body(customerRepository.findById(customerId));
	}

	
<<<<<<< HEAD
=======
>>>>>>> 4468398 (commit changes)
>>>>>>> feature
}

package com.jt.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.web.client.HttpClientErrorException.NotFound;

import com.jt.entity.CustomerEntity;
import com.jt.errors.ApiErrors;
import com.jt.exception.ResourceNotFoundException;
import com.jt.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{
    
	@Autowired
	CustomerRepository customerRepository;
	
	@Override
	@Transactional
	public ResponseEntity<CustomerEntity> saveCustomer(CustomerEntity customerEntity/*,Errors errors*/) {  
	/*if(errors.hasErrors()) {
	List<String> fieldErrorList = errors.getFieldErrors().stream().map(fe->fe.getDefaultMessage()).collect(Collectors.toList());
	ApiErrors errorResponse = new ApiErrors("API_BAD_REQUEST", fieldErrorList);	
	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);	*/
		try{
		return new ResponseEntity<>(customerRepository.save(customerEntity),HttpStatus.CREATED);
		}catch(Exception ex) {
		return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
	/*}
	return ResponseEntity.status(HttpStatus.CREATED).body(customerRepository.save(customerEntity));*/
	}
	
	@Override
	@Transactional
	public ResponseEntity<CustomerEntity> getCustomerById(Long customerId) throws ResourceNotFoundException  {
		System.out.println("in customer service getCustomerById()  called - customerId= "+customerId);
		try {
	//if(customerId !=null) {
	//ApiErrors errorResponse = new ApiErrors("CUSTOMER_NOT_FOUND", Arrays.asList("Customer id not found or invalid"));	
	  //return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
	//}
	return new ResponseEntity<>(customerRepository.findById(customerId).get(),HttpStatus.OK);
	//return new ResponseEntity<>(HttpStatus.OK).body(customerRepository.findById(customerId));
	}catch(Exception e) {
		throw new ResourceNotFoundException("Customer not found/invalid with id " +customerId);
	}
		//return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	
}

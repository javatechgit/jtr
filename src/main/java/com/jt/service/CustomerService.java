package com.jt.service;


import org.springframework.validation.Errors;
import com.jt.entity.CustomerEntity;


public interface CustomerService {
	 public Object saveCustomer(CustomerEntity customerEntity,Errors errors); 
	 public Object getCustomerById(Long customerId);
	 
}

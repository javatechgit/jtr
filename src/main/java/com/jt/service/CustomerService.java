package com.jt.service;

<<<<<<< HEAD
import java.util.Optional;
import com.jt.entity.CustomerEntity;

public interface CustomerService {
	 public CustomerEntity saveCustomerEntity(CustomerEntity customerEntity); 
	 public Optional<CustomerEntity> findById(Long customerId);
=======

import org.springframework.validation.Errors;
import com.jt.entity.CustomerEntity;


public interface CustomerService {
	 public Object saveCustomer(CustomerEntity customerEntity,Errors errors); 
	 public Object getCustomerById(Long customerId);
	 
>>>>>>> 4468398 (commit changes)
}

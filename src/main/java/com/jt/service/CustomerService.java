package com.jt.service;

<<<<<<< HEAD
=======
<<<<<<< HEAD
import java.util.Optional;
import com.jt.entity.CustomerEntity;

public interface CustomerService {
	 public CustomerEntity saveCustomerEntity(CustomerEntity customerEntity); 
	 public Optional<CustomerEntity> findById(Long customerId);
=======
>>>>>>> feature

import org.springframework.validation.Errors;
import com.jt.entity.CustomerEntity;


public interface CustomerService {
	 public Object saveCustomer(CustomerEntity customerEntity,Errors errors); 
	 public Object getCustomerById(Long customerId);
	 
<<<<<<< HEAD
=======
>>>>>>> 4468398 (commit changes)
>>>>>>> feature
}

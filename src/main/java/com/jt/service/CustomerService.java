package com.jt.service;

import java.util.Optional;
import com.jt.entity.CustomerEntity;

public interface CustomerService {
	 public CustomerEntity saveCustomerEntity(CustomerEntity customerEntity); 
	 public Optional<CustomerEntity> findById(Long customerId);
}

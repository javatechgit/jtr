package com.jt.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.entity.CustomerEntity;
import com.jt.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{
    
	@Autowired
	CustomerRepository customerRepository;
	
	
	 public CustomerEntity saveCustomerEntity(CustomerEntity customerEntity) {
	        return customerRepository.save(customerEntity);
	    }
	 public Optional<CustomerEntity> findById(Long customerId) {
		 return customerRepository.findById(customerId);
	 }
}

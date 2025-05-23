package com.jt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jt.entity.CustomerEntity;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity,Long> {
}

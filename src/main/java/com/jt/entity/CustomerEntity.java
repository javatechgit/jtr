package com.jt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
<<<<<<< HEAD
import lombok.Data;

@Data
=======
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
>>>>>>> 4468398 (commit changes)
@Entity
@Table(name = "RETAIL_CUSTOMERS")
public class CustomerEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUSTOMER_ID")
    private Long customerId;
	
	@NotEmpty(message = "customer first name is required")
    @Column(name = "CUSTOMER_FNAME")
    private String customerFName;
    
	@NotEmpty(message = "customer last name is required")
    @Column(name = "CUSTOMER_LNAME")
    private String customerLName;
	
}

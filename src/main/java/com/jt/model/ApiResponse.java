package com.jt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
	private String success; 
    private Long customerId;
    private String customerFName;
    private String customerLName;
    
    private Double lastMonthTransactionsAmt;
    private Double lastSecondMonthTransactionsAmt;
    private Double lastThirdMonthTransactionsAmt;
    private Double totalTransactionsAmt;
    
    private Long lastMonthRwdPoints;
    private Long lastSecondMonthRwdPoints;
    private Long lastThirdMonthRwdPoints;
    private Long totalRewardPoints;
   
    
    
    
    
	
	
	
} 

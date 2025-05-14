package com.jt.test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.entity.CustomerEntity;
import com.jt.repository.CustomerRepository;
import com.jt.service.CustomerServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc
public class RetailRewardControllerLayerTest {

	 @Autowired
	 private MockMvc mockMvc;
	 
	 @Mock
	 private CustomerRepository customerRepository;
	 
	 @Mock 
	 private CustomerServiceImpl customerService;
	
	 @Test
	  public void test_getAllTransactionsRewardsById_Endpoint() throws Exception {
	   mockMvc.perform(MockMvcRequestBuilders.get("/api/retail/rewards/1"))
	      .andExpect(MockMvcResultMatchers.status().isOk());
	  }
	 
	    @Test
	    public void test_createCustomer_EndPoint_success() throws Exception {

	    	CustomerEntity mockCustomer = CustomerEntity.builder()
					.customerFName("CustomerFName")
					.customerLName("CustomerLName").build();

	        when(customerRepository.save(any())).thenReturn(Boolean.TRUE);

	        mockMvc.perform(
	                MockMvcRequestBuilders.post("/customer/saveCustomer")
	                        .content(asJsonString(mockCustomer))
	                        .contentType(MediaType.APPLICATION_JSON)
	                        .accept(MediaType.APPLICATION_JSON)
	        )
	                .andExpect(status().isOk())
	                .andExpect(content().string("Customer Save Done"));
	    }
	    
	    public static String asJsonString(final Object obj) {
	        try {
	            return new ObjectMapper().writeValueAsString(obj);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }
	
}

package com.jt.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;

import com.jt.repository.CustomerRepository;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.jt.controller.RetailRewardController;
import com.jt.entity.CustomerEntity;
import com.jt.entity.TransactionEntity;
import com.jt.exception.ResourceNotFoundException;
import com.jt.model.ApiResponse;
import com.jt.repository.TransactionRepository;
import com.jt.service.CustomerServiceImpl;
import com.jt.service.TransactionServiceImpl;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.test.web.servlet.RequestBuilder;

//This is Integration test which requires a class annotated with @SpringBootTest so that controllers, services, repos, and models are made available for use
// It also requires an @Autowired MockMvc.

@SpringBootTest
@AutoConfigureMockMvc
//@WebMvcTest(value = RetailRewardController.class)
//@WithMockUser
class RetailRewardApiApplicationTests {

	 /* to test for only controller GET and POST */
	/* use @WebMvcTest(value = RetailRewardController.class) at class level OR use @SpringBootTest
      @AutoConfigureMockMvc at class level to test controller GET POST   */
	 @Autowired
	 private MockMvc mockMvc;
	 
	@MockitoBean
	private TransactionServiceImpl transactionService;
	 
	 @MockitoBean
	 private CustomerServiceImpl customerService;
	
	
	 @MockitoBean
	 private CustomerRepository customerRepository;
	 
	 @MockitoBean
	 private TransactionRepository transactionRepository; 
	/* to test for only controller GET and POST */
	 
	 /* to test for all but not for controller GET and POST  
	 @Mock
	 private CustomerRepository customerRepository;
	  
	 @InjectMocks 
	 private CustomerServiceImpl customerService;
	 
	 @Mock
	 private TransactionRepository transactionRepository;
	 
	 @InjectMocks 
	 private TransactionServiceImpl transactionService; */
	 
	   ApiResponse apiResponse = ApiResponse.builder()
			   .success("Get All Transactions Successfully for Customer:")
			   .customerId(1L)
			   .customerFName("CustomerFName")
			   .customerLName("CustomerLName")
			   .lastMonthTransactionsAmt(390.0)
			   .lastSecondMonthTransactionsAmt(0.0)
			   .lastThirdMonthTransactionsAmt(0.0)
			   .totalTransactionsAmt(390.0)
			   .lastMonthRwdPoints(340L)
			   .lastSecondMonthRwdPoints(0L)
			   .lastThirdMonthRwdPoints(0L)
			   .totalRewardPoints(340L).build();
	   
	   String expectedApiRes = asJsonString(apiResponse);
			   
		
	    
	 
	 
	 
	 @Test
	 public void test_saveCustomerSuccess() {
		 CustomerEntity mockCustomer = CustomerEntity.builder()
					.customerFName("CustomerFName")
					.customerLName("CustomerLName").build();

		 Mockito.when(customerRepository.save(any(CustomerEntity.class))).thenReturn(mockCustomer);

	     CustomerEntity savedCustomer = customerRepository.save(mockCustomer);
	     Assertions.assertNotNull(savedCustomer);
	     Assertions.assertTrue(savedCustomer.getCustomerFName().equals("CustomerFName"));
	     Assertions.assertEquals(savedCustomer.getCustomerLName(),"CustomerLName");
	 }
	 
	 @Test
	 public void test_saveTransactionSuccess() {
			TransactionEntity mockTransaction = TransactionEntity.builder()
	    			.customerId(1L)
	    			.transactionDate(Timestamp.valueOf("2025-05-06 00:00:00.0"))
	    			.transactionAmount(70.0).build();

		Mockito.when(transactionRepository.save(any(TransactionEntity.class))).thenReturn(mockTransaction);

		TransactionEntity savedTransaction = transactionRepository.save(mockTransaction);
		Assertions.assertNotNull(savedTransaction);
	    Assertions.assertTrue(savedTransaction.getTransactionDate().equals(Timestamp.valueOf("2025-05-06 00:00:00.0")));
	    Assertions.assertEquals(savedTransaction.getTransactionAmount(),70.0);
	
	 }
	
	 /*
	 @Test
	 public void createEmployeeAPI() throws Exception 
	 {
		 mockMvc.perform( MockMvcRequestBuilders
	 	      .post("/api/retail/customers")
	 	      .content(asJsonString(new CustomerEntity(null, "firstName4", "lastName4")))
	 	      .contentType(MediaType.APPLICATION_JSON)
	 	      .accept(MediaType.APPLICATION_JSON))
	       .andExpect(status().isCreated())
	       .andExpect(MockMvcResultMatchers.jsonPath("$.customerId").exists());
	 }
	 
	 @Test
	    public void test_createCustomer_EndPoint_Success() throws Exception {

	    	CustomerEntity mockCustomer = CustomerEntity.builder()
					.customerId(6L)
					.customerFName("CustomerFNameNew")
					.customerLName("CustomerLNameNew").build();

	    	Mockito.when(customerRepository.save(any(CustomerEntity.class))).thenReturn(mockCustomer);

	        mockMvc.perform(
	                MockMvcRequestBuilders.post("http://localhost:9090/api/retail/customers")
	                        .content(asJsonString(mockCustomer))
	                        .contentType(MediaType.APPLICATION_JSON)
	                        .accept(MediaType.APPLICATION_JSON)
	                        .secure(false)
	        )
	                .andExpect(status().isCreated())// error expected 201 but 404
	                .andExpect(content().string("Customer Save Done"));
	    }
	 */
	/* need this fullfil 
	 ResultActions.andExpect(mockMvc.perform(get("/person/1"))
   .andExpect(status().isOk())
   .andExpect(content().contentType(MediaType.APPLICATION_JSON))
   .andExpect(jsonPath("$.person.name").value("Jason"));
 )*/
	 
	  
	 
	 @Test
	 public void test_getAllTransactionsRewardsById_Endpoint() throws Exception {
		 System.out.println(expectedApiRes);
		// System.out.println(transactionService.getTransactionRewardsById(1L));
		// Mockito.when( (Object) transactionService.getTransactionRewardsById( Mockito.anyLong()) ).thenReturn(apiResponse);
		// Mockito.when( transactionService.getTransactionRewardsById(1L) ).thenReturn(apiResponse);
		 
	   //ResultActions resultActions = 
		 MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/retail/rewards/1").accept(MediaType.APPLICATION_JSON);
		 
		 ResultActions resultActions = mockMvc.perform(requestBuilder);
		 resultActions.andExpect(status().isOk());
		
		// .andExpect(content().contentType(MediaType.APPLICATION_JSON))
		 //.andExpect(jsonPath("$.success").exists())
		 //.andExpect(jsonPath("$.customerId").isNotEmpty());
		 
		// Assertions.assertTrue(resultActions.andReturn().getResponse().getContentAsString().equals(expectedApiRes));
		// MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		 //MvcResult result =  mockMvc.perform(MockMvcRequestBuilders.get("/api/retail/rewards/1").accept(MediaType.APPLICATION_JSON)).andReturn();
		
		// System.out.println("getContentAsString="+result.getResponse().getContentAsString());
		 String expectedApiRes = asJsonString(apiResponse);
		 System.out.println("expectedApiRes ="+expectedApiRes);
		 
		 Assertions.assertNotNull(resultActions);
		 Assertions.assertEquals(resultActions.andReturn().getResponse().getStatus(), 200);
		//String expected = "{\"id\":\"Course1\",\"name\":\"Spring\",\"description\":\"10 Steps\"}";

		// JSONAssert.assertEquals(expectedApiRes, result.getResponse()
			//		.getContentAsString(), false);
		 
	     // .andExpect(MockMvcResultMatchers.status().isOk());  
			   //.accept(MediaType.APPLICATION_JSON))
			  // .andDo(print())
			  //.andExpect(MockMvcResultMatchers.status().isOk());
			   //.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			  // .andExpect(MockMvcResultMatchers.jsonPath("$.success").exists())
			   //.andExpect(MockMvcResultMatchers.jsonPath("$.customerId").isNotEmpty())

			   
			   
	   // .andExpect(MockMvcResultMatchers.jsonPath("$.employees[*].employeeId").isNotEmpty());
	//System.out.println("resultActions="+resultActions);
	 // MvcResult mvcResult = resultActions.andReturn();
	  //System.out.println("mvcResult="+mvcResult.getAsyncResult());
	 // System.out.println("mvcResult res="+mvcResult.getResponse().getContentAsString());
	 // System.out.println("mvcResult err="+mvcResult.getResponse().getErrorMessage());
	 // System.out.println("mvcResult int="+mvcResult.getResponse().getStatus());
	       
	    
	    //.andExpect(MockMvcResultMatchers.status().isOk());
	  }
	 /*
	 @Test
	 public void test_getAllTransactionsRewardsById_With_Data_Endpoint() throws Exception {
		 String apiRes = "{\r\n"
		 		+ "    \"success\": \"Get All Transactions Successfully for Customer:\",\r\n"
		 		+ "    \"customerId\": 1,\r\n"
		 		+ "    \"customerFName\": \"CustomerFName\",\r\n"
		 		+ "    \"customerLName\": \"CustomerLName\",\r\n"
		 		+ "    \"lastMonthTransactionsAmt\": 390.0,\r\n"
		 		+ "    \"lastSecondMonthTransactionsAmt\": 0.0,\r\n"
		 		+ "    \"lastThirdMonthTransactionsAmt\": 0.0,\r\n"
		 		+ "    \"totalTransactionsAmt\": 390.0,\r\n"
		 		+ "    \"lastMonthRwdPoints\": 340,\r\n"
		 		+ "    \"lastSecondMonthRwdPoints\": 0,\r\n"
		 		+ "    \"lastThirdMonthRwdPoints\": 0,\r\n"
		 		+ "    \"totalRewardPoints\": 340\r\n"
		 		+ "}";
		 JSONObject obj = new JSONObject(apiRes);
		 ObjectMapper objMap = new ObjectMapper();
		 
		 MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(
					"/api/retail/rewards/1").accept(
					MediaType.APPLICATION_JSON).content(apiRes).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		 // MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		 System.out.println("result.gerRespnse ="+result.getResponse().getContentAsString());
		 
		 
		// System.out.println("apiResObj="+obj);
		// System.out.println("totalRewardPoints value from apiResObj="+obj.getLong("totalRewardPoints"));
		// System.out.println("totalRewardPoints value from apiResObj="+obj.getJSONObject("totalRewardPoints").getLong("340L"));
		// String tot = JsonPath.read(apiRes, "$.totalRewardPoints");
		 //System.out.println("tot="+tot);
	//	 mockMvc.perform(get("/api/retail/rewards/1"))
	 //  .andExpect(status().isOk());
	  // .andExpect(jsonPath("$.person.name").value("Jason"));
	
	// .andExpect(jsonPath("$.customerFName").value("CustomerFName"));
	 } */
	 /*
	 @Test
	 public void createEmployeeAPI() throws Exception 
	 {
		 CustomerEntity ent = new CustomerEntity(null, "firstName4", "lastName4");
		 ObjectMapper objMap = new ObjectMapper();
		String s = objMap.writeValueAsString(ent);
		System.out.println("s="+s);
		 
		 mockMvc.perform( MockMvcRequestBuilders.post("/api/retail/customers").content(asJsonString(ent))
				 .contentType(MediaType.APPLICATION_JSON)
                 .accept(MediaType.APPLICATION_JSON))
		
		 .andExpect(MockMvcResultMatchers.status().isCreated())
		 .andExpect(content().string("Customer Save Done"));
		 
		   	 
    }*/
	 
	 @Test
	 public void test_findByCustomerId() throws ResourceNotFoundException {
		long customerId = 1L;
		CustomerEntity mockCustomer = CustomerEntity.builder()
				.customerId(customerId)
				.customerFName("CustomerFName")
				.customerLName("CustomerLName").build();
		
		String responseBody = "{\"customerId\": 1,\"customerFName\": \"CustomerFName\",\"customerLName\": \"CustomerLName\"}";
		
		ResponseEntity responseEntity = new ResponseEntity(responseBody,HttpStatus.OK);
		Mockito.when(customerService.getCustomerById(any(Long.class))).thenReturn(responseEntity);
		//Object result =  customerService.getCustomerById(1L);
		//Optional<CustomerEntity> dbCustomer =customerRepository.findById(customerId);
		ResponseEntity<CustomerEntity> dbCustomerResult =customerService.getCustomerById(customerId);
		//ResponseEntity<?> response =  customerService.getCustomerById(1L);
		//System.out.println("byCid response"+response);
		//System.out.println("byCid response.getStatusCode"+response.getStatusCode());
		//System.out.println("HttpStatus.OK "+HttpStatus.OK);
		//System.out.println("byCid response.body"+response.getBody());
		
		//boolean instanceOf =response.getBody() instanceof CustomerEntity;
		//System.out.println("instanceOf="+instanceOf);
		
		 // Assert the response
		//Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		//Assertions.assertNotNull(response.getBody());
		//Assertions.assertTrue(response.getBody() instanceof CustomerEntity);
		//CustomerEntity entity = (CustomerEntity) response.getBody();
		//System.out.println("entity class cast ="+entity);
		//Assertions.assertEquals("CustomerFName", entity.getCustomerFName());
		
		Assertions.assertNotNull(dbCustomerResult);
	    Assertions.assertEquals(dbCustomerResult.getBody(),responseBody);
	   // Assertions.assertEquals(dbCustomerResult.getBody().getCustomerFName(),"CustomerFName");
      
		
		//Object obj = response.getBody();
	//	System.out.println("obj="+obj);
		//HttpHeaders htpHeaders = response.getHeaders();
		//System.out.println("htpHeaders="+htpHeaders);
		//CustomerEntity esult =  customerService.getCustomerById(1L).getBody();
	    //Assertions.assertNotNull(result);
		
		 }
	 //correct 
	  @Test
	  public void test_findByTransactionId() throws ResourceNotFoundException {
    	long transactionId = 1L;
    	long customerId = 1L;
    	TransactionEntity mockTransaction = TransactionEntity.builder()
    			.transactionId(transactionId)
    			.customerId(customerId)
    			.transactionDate(Timestamp.valueOf("2025-05-06 00:00:00.0"))
    			.transactionAmount(70.0).build();

	     Mockito.when(transactionRepository.findById(transactionId)).thenReturn(Optional.of(mockTransaction)); 
       //  Object result = transactionService.getTransactionById(transactionId);	 
         Object result= transactionService.getTransactionById(transactionId);	 
         Assertions.assertNotNull(result);
	 }
 
	 
	 	 
	  @Test 
	  public void test_totalRewardPoints_firstAmountForRwrd_PrivateMethod() {
	  double firstAmountForRwrdBtw50and100 = 80;
	  TransactionEntity mockTransaction = TransactionEntity.builder()
    			.transactionId(1L)
    			.customerId(1L)
    			.transactionDate(Timestamp.valueOf("2025-05-06 00:00:00.0"))
    			.transactionAmount(firstAmountForRwrdBtw50and100).build();
	
	  Long totPoints= ReflectionTestUtils.invokeMethod(transactionService,"totalRewardPoints",mockTransaction); 
	  Assertions.assertEquals(30, totPoints); 
	  }
		
		
	  @Test 
	  public void test_totalRewardPoints_secondAmountForRwrd_PrivateMethod() {
		  double secondAmountForRwrdAbv100 = 120;
		  TransactionEntity mockTransaction = TransactionEntity.builder()
				  .transactionId(1L)
				  .customerId(1L)
				  .transactionDate(Timestamp.valueOf("2025-05-06 00:00:00.0"))
				  .transactionAmount(secondAmountForRwrdAbv100).build();
		  
		  Long totPoints= ReflectionTestUtils.invokeMethod(transactionService,"totalRewardPoints",mockTransaction); 
		  Assertions.assertEquals(90, totPoints); 
	  }
	
	  public static String asJsonString(final Object obj) {
	        try {
	            return new ObjectMapper().writeValueAsString(obj);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }

}

package com.jt.test;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.Format;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.jt.controller.RetailRewardController;
import com.jt.entity.CustomerEntity;
import com.jt.repository.CustomerRepository;
import com.jt.repository.TransactionRepository;
import com.jt.service.CustomerServiceImpl;
import com.jt.service.TransactionServiceImpl;

// This is Unit Test(only one - controller class need to test ) for Controller layer which is not require @SpringBootTest , we can use 
// (no need to use this @AutoConfigureMockMvc if using @WebMvcTest, this @AutoConfigureMockMvc require with @SpringBootTest)
// @WebMvcTest - is used to test for MVC app  specially Controller related class 
@WebMvcTest(RetailRewardController.class) // this means only one class controller class- RetailRewardController will be run
@EnableAsync
public class RetailRewardControllerLayerTest {

	 @Autowired
	 private MockMvc mockMvc;
	 
	 @MockitoBean
	 private CustomerServiceImpl customerService;
	
	 @MockitoBean
	 private CustomerRepository customerRepository;
	 
	 @MockitoBean
	 private TransactionServiceImpl transactionService;
	 
	 @MockitoBean
	 private TransactionRepository transactionRepository; 
	 
	 
	 @Test
	 @Disabled
	    public void testGetRequestWithJsonContent() throws Exception { // not working with data 
		 long customerId=1;
		 CustomerEntity mockCustomer =  CustomerEntity.builder()
					.customerFName("CustomerFName")
					//.customerLName("CustomerLName")
					.build();
			Mockito.when(customerRepository.findById(customerId)).thenReturn(Optional.of(mockCustomer));// content type not set error 
			//Mockito.when(customerService.getCustomerById(any(CustomerEntity.class))).
	        String expectedJson = "{\"customerFName\": \"CustomerFName\"}";
	        JSONObject jsonObject = new JSONObject(expectedJson);
	        System.out.println("json object="+jsonObject);
	        String name = jsonObject.optString("customerFName", null);
	        System.out.println("name="+name);
	      //  System.out.println(expectedJson);
	        /*MvcResult result = */	     mockMvc.perform(get("/api/retail/customers/"+customerId)
	               .accept(MediaType.APPLICATION_JSON)) // Specify expected content type
	        .andDo(print())
	       // .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	               .andExpect(status().isOk());
	             //  .andExpect(content().contentType(MediaType.APPLICATION_JSON))// this resolve unparsable json error 
	              // .andExpect(content().json(requestBodyAsJson(mockCustomer))) // Expecting JSON content
	      //  .andExpect(jsonPath("$.customerFName").value(mockCustomer.getCustomerFName()));
	       // .andReturn();
	       // System.out.println("result="+result.getResponse().getContentAsString());
				   

	    }
		 
	@Test
	@Disabled
	public void test_createCustomer_EndPoint() throws Exception {
		CustomerEntity mockCustomer = CustomerEntity.builder()
				//.customerId(anyLong())
				.customerFName("CFNamePOST")
				.customerLName("CLNamePOST").build();
		//String expectedJson = "{\"customerId\": 100,\"customerFName\": \"CFNamePOST\",\"customerLName\": \"CLNamePOST\"}";
		String expectedJson = "{\"customerId\": 100,\"customerFName\": \"CFNamePOST\",\"customerLName\": \"CLNamePOST\"}";
		Mockito.when(customerRepository.save(any())).thenReturn(Boolean.TRUE);
		 MvcResult result =			mockMvc.perform(post("/api/retail/customers").content(requestBodyAsJson(mockCustomer)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isCreated())
		.andExpect(content().string(expectedJson))
		.andReturn();
		System.out.println("post result ="+result);
		System.out.println("post result ="+result.getResponse().getContentAsString());
		Assertions.assertNotNull(result);
		Assertions.assertEquals(result.getResponse().getStatus(),201);
		
		
	}
	 @Disabled
	 @Test
	  public void test_getCustomerEntityById_Endpoint() throws Exception {
		 CustomerEntity mockCustomer = CustomerEntity.builder()
					.customerId(1L)
					.customerFName("CustomerFName")
					.customerLName("CustomerLName").build();
		 
		 
		 
		// ResponseEntity<?> response = customerService.getCustomerById(Mockito.anyLong());
		 //CustomerEntity entity = (CustomerEntity) response.getBody();
		// Mockito.when(customerRepository.findById(1L)).thenReturn(Optional.of(mockCustomer));// content type not set 
		 
		// ObjectMapper obj = new ObjectMapper();
		 //obj.writeValueAsString(mockCustomer);
		 
		 //Expected JSON response
		// String expectedJson  =  "{\"customerId\":1,\"customerFName\":\"CustomerFName\",\"customerLName\":\"CustomerLName\"}";
		// String expectedJson  = "{\r\n"\"customerId\": 1,\r\n""    \"customerFName\": \"CustomerFName\",\r\n" + "    \"customerLName\": \"CustomerLName\"\r\n"+ "}";
		 
		 // By Id  
		 final long customerId=1;
		 
		 //*set up GET request (with Path variable) - when run this, sysout from RetailRewardController called (GET in contrller  customerId= 1)
		 MvcResult result =	 mockMvc.perform(get("/api/retail/customers/"+customerId).accept(MediaType.APPLICATION_JSON))
		//* print response
		 .andDo(print())
		 // * expect status 200 OK
		  .andExpect(status().isOk())
		 //* expect return Content-Type header as application/json
		// .andExpect(content().contentType(MediaType.APPLICATION_JSON))
		 //  .andExpect(content().json(expectedJson)); 
		 // * return 
		 .andReturn();
		// confirm returned JSON values
		// .andExpect(jsonPath("customerId").value(customerId))
		 //.andExpect(jsonPath("customerFName").value("CustomerFName"))
		 //.andReturn();
		 
		// Get the actual JSON response
		 System.out.println("result="+result.getResponse());
		 System.out.println("result="+result.getResponse().getContentAsString());
	    // String actualJson = result.getResponse().getContentAsString();
	    /*
	    CustomerEntity cEntity = obj.readValue(actualJson, CustomerEntity.class);
	    Assertions.assertNotNull(cEntity);
	    Assertions.assertEquals(1L, cEntity.getCustomerId());
	    Assertions.assertEquals("CustomerFName", cEntity.getCustomerFName());
	    */
	    
	  //  JsonPath.parse(actualJson).read("$[0].customerId");
	    Assertions.assertNotNull(result);
		Assertions.assertEquals(result.getResponse().getStatus(), 200);
		// Assert that the actual JSON matches the expected JSON
	  // JSONAssert.assertEquals(expectedJson, actualJson, false);

		 
		 
		 
		 
		 
	//   mockMvc.perform(MockMvcRequestBuilders.get("/api/retail/rewards/1"))
	  //    .andExpect(MockMvcResultMatchers.status().isOk());
	  }
	 
	 
	/* @Test
	  public void test_getAllTransactionsRewardsById_Endpoint() throws Exception {
	   mockMvc.perform(MockMvcRequestBuilders.get("/api/retail/rewards/1"))
	      .andExpect(MockMvcResultMatchers.status().isOk());
	  }*/
	 
	 
	    // perfect working POST with jsonPath data/ without data- 
	    @Test
	    public void test_createCustomer_POST_EndPoint() throws Exception {
	    //	CustomerEntity reuestBody = new CustomerEntity(1l,"CustomerPostNewF","CustomerPostNewL");
	    	
	    	CustomerEntity reuestBody = CustomerEntity.builder()
					.customerId(1l) // in request body customerId is auto generated id in actual operation, so optional
					.customerFName("CustomerPostNewF")
					.customerLName("CustomerPostNewL").build();
	    	//System.out.println("mockCustomer ="+mockCustomer);
	    	String responseBody = "{\"customerId\": 1,\"customerFName\": \"CustomerPostNewF\",\"customerLName\": \"CustomerPostNewL\"}";
			
// Mockito.when for save method, not for findById
ResponseEntity responseEntity = new ResponseEntity(responseBody, HttpStatus.OK);
// mockito behaviour for save customer 
Mockito.when(customerService.saveCustomer(any(CustomerEntity.class))).thenReturn(responseEntity);
//Mockito.when(customerService.getCustomerById(any(Long.class))).thenReturn(responseEntity);
//this stub-findById is wrong, should be for save Mockito.when(customerRepository.findById(1l)).thenReturn(Optional.of(mockCustomer));
	    	
	    	mockMvc.perform(MockMvcRequestBuilders.post("/api/retail/customers")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(requestBodyAsJson(reuestBody))) //this is request body data,same type as that passing in Mockito.when saveCustomer(CustomerEntity.class)
	        .andDo(print())        
	    	.andExpect(MockMvcResultMatchers.status().isOk())// Ok status, not Created status
                .andExpect(content().string(responseBody))// this is ResponseBody data same as type in thenReturn(responseEntity)
                .andExpect(MockMvcResultMatchers.jsonPath("$.customerId", Matchers.is(1)))// with content details check 
                .andExpect(MockMvcResultMatchers.jsonPath("$.customerFName",Matchers.is("CustomerPostNewF")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.customerLName",Matchers.is("CustomerPostNewL")));
    	
	    	/*

	    	Mockito.when(customerRepository.save(any(CustomerEntity.class))).thenReturn(mockCustomer);
	    	//Mockito.when(customerRepository.save(any())).thenReturn(Boolean.TRUE);

	        mockMvc.perform(post("/api/retail/customers")
	        		.contentType(MediaType.APPLICATION_JSON)
	              //  .content(new ObjectMapper().writeValueAsString(mockCustomer))
	        )
	       // .andExpect(content().contentType(MediaType.APPLICATION_JSON))        
	        .andExpect(status().isCreated())
	        
	       // .andExpect(content().contentType("application/json;charset=UTF-8"))
	        //.andExpect(content().contentType("application/json;charset=UTF-8"))
	      // errorNo value at JSON path -   .andExpect( jsonPath("$.customerFName").value(mockCustomer.getCustomerFName())  );
	        .andExpect( jsonPath("$.customerFName",is("CustomerPostNewF")  ));
	                //.andExpect(content().string("Customer Save Done")); */
	    }
	    
	 // perfect working GET with jsonPath data /without data- 
	    @Test
	    public void test_getCustomerEntityById_GET_EndPoint() throws Exception {
	    //	CustomerEntity reuestBody = new CustomerEntity(1l,"CustomerPostNewF","CustomerPostNewL");
	   	 // By Id  
			 final long customerId=1;
	    	CustomerEntity reuestBody = CustomerEntity.builder()
					.customerId(1l) // in request body customerId is auto generated id in actual operation, so optional
					.customerFName("CustomerGetF")
					.customerLName("CustomerGetL").build();
	    	//System.out.println("mockCustomer ="+mockCustomer);
	    	
	    	String responseBody = "{\"customerId\": 1,\"customerFName\": \"CustomerGetF\",\"customerLName\": \"CustomerGetL\"}";
			

ResponseEntity responseEntity = new ResponseEntity(responseBody, HttpStatus.OK);
//Mockito.when for save method, not for findById
//Mockito.when(customerService.saveCustomer(any(CustomerEntity.class))).thenReturn(responseEntity);
// Mockito behaviour for getCustomerById
Mockito.when(customerService.getCustomerById(any(Long.class))).thenReturn(responseEntity);
//this stub-findById is wrong, should be for save Mockito.when(customerRepository.findById(1l)).thenReturn(Optional.of(mockCustomer));
	    	
	    	mockMvc.perform(MockMvcRequestBuilders.get("/api/retail/customers/"+customerId)
	    			.accept(MediaType.APPLICATION_JSON))
	                //.contentType(MediaType.APPLICATION_JSON)
	              // no request body in GET  .content(requestBodyAsJson(reuestBody))) //this is request body data,same type as that passing in Mockito.when saveCustomer(CustomerEntity.class)
	        .andDo(print())        
	    	.andExpect(MockMvcResultMatchers.status().isOk())// Ok or Accepted, not Created
	                .andExpect(content().string(responseBody))// this is ResponseBody data same as type in thenReturn(responseEntity)
	                .andExpect(MockMvcResultMatchers.jsonPath("$.customerId", Matchers.is(1)))// with content details check 
	                .andExpect(MockMvcResultMatchers.jsonPath("$.customerFName",Matchers.is("CustomerGetF")))
	                .andExpect(MockMvcResultMatchers.jsonPath("$.customerLName",Matchers.is("CustomerGetL")));
	    }
	    
 // perfect working GET with jsonPath data /without data- 
@Test
public void test_getAllTransactionsRewardsById_GET_EndPoint() throws Exception {
//	CustomerEntity reuestBody = new CustomerEntity(1l,"CustomerPostNewF","CustomerPostNewL");
 // By Id  
	 final long customerId=1;
	CustomerEntity reuestBody = CustomerEntity.builder()
	.customerId(1l) // in request body customerId is auto generated id in actual operation, so optional
	.customerFName("CustomerGetF")
	.customerLName("CustomerGetL").build();
	//System.out.println("mockCustomer ="+mockCustomer);
    	
String responseBody = "{\"success\": \"Get All Transactions Successfully for Customer:\",\"customerId\": 1,\"customerFName\": \"CustomerFName\",\"customerLName\": \"CustomerLName\",\"lastMonthTransactionsAmt\": 390.0,\"lastSecondMonthTransactionsAmt\": 0.0,\"lastThirdMonthTransactionsAmt\":0.0,\"totalTransactionsAmt\":390.0,\"totalRewardPoints\":340}";

ResponseEntity responseEntity = new ResponseEntity(responseBody, HttpStatus.OK);
//Mockito.when for save method, not for findById
//Mockito.when(customerService.saveCustomer(any(CustomerEntity.class))).thenReturn(responseEntity);
// Mockito behaviour for getCustomerById
Mockito.when(transactionService.getTransactionRewardsById(any(Long.class))).thenReturn(responseEntity);
//this stub-findById is wrong, should be for save Mockito.when(customerRepository.findById(1l)).thenReturn(Optional.of(mockCustomer));
	    	
	mockMvc.perform(MockMvcRequestBuilders.get("/api/retail/rewards/"+customerId)
			.accept(MediaType.APPLICATION_JSON))
            //.contentType(MediaType.APPLICATION_JSON)
          // no request body in GET  .content(requestBodyAsJson(reuestBody))) //this is request body data,same type as that passing in Mockito.when saveCustomer(CustomerEntity.class)
    .andDo(print())        
	.andExpect(MockMvcResultMatchers.status().isOk())// Ok 
            .andExpect(content().string(responseBody))// this is ResponseBody data same as type in thenReturn(responseEntity)
            .andExpect(MockMvcResultMatchers.jsonPath("$.customerId", Matchers.is(1)))// with content details check 
            .andExpect(MockMvcResultMatchers.jsonPath("$.customerFName",Matchers.is("CustomerFName")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.customerLName",Matchers.is("CustomerLName")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.lastMonthTransactionsAmt",Matchers.is(390.0)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.lastSecondMonthTransactionsAmt",Matchers.is(0.0)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.lastThirdMonthTransactionsAmt",Matchers.is(0.0)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.totalTransactionsAmt",Matchers.is(390.0)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.totalRewardPoints",Matchers.is(340)));
	    }

	    
    public static String requestBodyAsJson(final Object obj) {
        try {
           System.out.println("requestBodyAsJson="+new ObjectMapper().writeValueAsString(obj));
        	return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
	
}

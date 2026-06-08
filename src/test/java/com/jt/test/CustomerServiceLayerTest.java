package com.jt.test;

import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.jt.entity.CustomerEntity;
import com.jt.repository.CustomerRepository;
import com.jt.service.CustomerServiceImpl;

@SpringBootTest
public class CustomerServiceLayerTest {
	
	@InjectMocks
	private CustomerServiceImpl customerService;
	
	@Mock
	private CustomerRepository customerRepository;
	
	@Test
	 public void test_saveCustomerSuccess() {
		 CustomerEntity mockCustomer = CustomerEntity.builder()
					.customerFName("CustomerFName")
					.customerLName("CustomerLName").build();

		 Mockito.when(customerRepository.save(any(CustomerEntity.class))).thenReturn(mockCustomer);

	     CustomerEntity savedCustomer = customerRepository.save(mockCustomer);
	    // Assertions.assertEquals(expMock, actualDB);
	     Assertions.assertTrue(savedCustomer.getCustomerFName().equals("CustomerFName"));
	 }
	
	/*
	Service Layer Test format -
	class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void getUserById_existingUser_returnsUser() {
        // Arrange
        Long userId = 1L;
        User mockUser = new User(userId, "Test User");
        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));

        // Act
        User result = userService.getUserById(userId);

        // Assert
        assertEquals(mockUser, result);
    } 
	 
	 
*/
}

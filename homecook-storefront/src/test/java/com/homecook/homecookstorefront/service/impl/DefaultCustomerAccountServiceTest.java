package com.homecook.homecookstorefront.service.impl;

import com.homecook.homecookentity.entity.CustomerEntity;
import com.homecook.homecookentity.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DefaultCustomerAccountServiceTest
{
    private static final String TEST_CUSTOMER_ACCOUNT = "testAccount";
    private static final String TEST_PASSWORD = "testPassword";

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    CustomerEntity customer;

    @InjectMocks
    private DefaultCustomerAccountService customerAccountService;

    @BeforeEach
    void setUp()
    {
        given(customer.getAccount()).willReturn(TEST_CUSTOMER_ACCOUNT);
    }


    @Test
    public void testRegisterSuccess()
    {
        customerAccountService.register(customer, TEST_PASSWORD);
        verify(customerRepository).save(customer);
    }
}
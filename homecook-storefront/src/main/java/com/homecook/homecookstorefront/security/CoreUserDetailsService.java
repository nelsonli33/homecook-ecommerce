package com.homecook.homecookstorefront.security;

import com.homecook.homecookentity.entity.CustomerEntity;
import com.homecook.homecookstorefront.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service(value = "coreUserDetailsService")
public class CoreUserDetailsService implements UserDetailsService
{
    private CustomerService customerService;

    @Autowired
    public CoreUserDetailsService(CustomerService customerService)
    {
        this.customerService = customerService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {

        final Optional<CustomerEntity> customerEntity = customerService.getCustomerForUid(username);

        if (!customerEntity.isPresent())
        {
            throw new UsernameNotFoundException("Customer: " + username + " not found.");
        }

        CustomerEntity customer = customerEntity.get();

        final UserDetails userDetails = User.builder()
                .username(customer.getAccount())
                .password(customer.getPassword())
                .authorities(Arrays.asList())
                .build();

        return userDetails;
    }
}

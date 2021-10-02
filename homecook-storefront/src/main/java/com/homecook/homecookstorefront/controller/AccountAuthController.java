package com.homecook.homecookstorefront.controller;

import com.homecook.homecookstorefront.controller.mapper.AccountRestMapper;
import com.homecook.homecookstorefront.dto.CustomerDTO;
import com.homecook.homecookstorefront.dto.RegisterDTO;
import com.homecook.homecookstorefront.error.InternalErrorCode;
import com.homecook.homecookstorefront.exception.StorefrontServerRuntimeException;
import com.homecook.homecookstorefront.facade.CustomerFacade;
import com.homecook.homecookstorefront.model.Customer;
import com.homecook.homecookstorefront.model.CustomerAuthRequest;
import com.homecook.homecookstorefront.model.CustomerAuthResponse;
import com.homecook.homecookstorefront.model.RegisterCustomerRequest;
import com.homecook.homecookstorefront.util.JwtTokenUtil;
import com.homecook.homecookstorefront.util.ReqMsgValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountAuthController
{
    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;
    private CustomerFacade customerFacade;
    private AccountRestMapper accountRestMapper;

    @Autowired
    public AccountAuthController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, CustomerFacade customerFacade, AccountRestMapper accountRestMapper)
    {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.customerFacade = customerFacade;
        this.accountRestMapper = accountRestMapper;
    }


    @PostMapping(value = "/api/v1/customers/register")
    public ResponseEntity<Customer> registerCustomer(@RequestBody RegisterCustomerRequest registerCustomerRequest, BindingResult errors)
    {

        ReqMsgValidator.AccountAuth.validateRegisterCustomerRequest(registerCustomerRequest, errors);

        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setAccount(registerCustomerRequest.getAccount());
        registerDTO.setPassword(registerCustomerRequest.getPassword());
        registerDTO.setEmail(registerCustomerRequest.getEmail());

        final CustomerDTO registeredCustomer = customerFacade.register(registerDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(accountRestMapper.convertDTOtoResponse(registeredCustomer));
    }

    @PostMapping(value = "/api/v1/customers/login")
    public ResponseEntity<CustomerAuthResponse> login(@RequestBody CustomerAuthRequest customerAuthRequest, BindingResult errors)
    {
        ReqMsgValidator.AccountAuth.validateCustomerAuthRequest(customerAuthRequest, errors);

        try
        {
            final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(customerAuthRequest.getUid(), customerAuthRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            final String token = jwtTokenUtil.generateToken(authentication);

            CustomerAuthResponse response = new CustomerAuthResponse();
            response.setAccessToken(token);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(response);
        }
        catch (AuthenticationException ex)
        {
            throw new StorefrontServerRuntimeException(InternalErrorCode.CUSTOMER_LOGIN_INCORRECT, "Incorrect username or password", ex);
        }
    }
}

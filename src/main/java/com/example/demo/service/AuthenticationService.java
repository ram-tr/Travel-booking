package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.config.JwtService;
import com.example.demo.dto.CustomerDTO;
import com.example.demo.entity.Customer;
import com.example.demo.entity.LoginCredentials;
import com.example.demo.entity.Role;
import com.example.demo.exceptions.CustomerNotFoundException;
import com.example.demo.repository.CustomerRepo;


@Service
public class AuthenticationService {
	
	@Autowired
	private CustomerRepo customerRepo;
	
	private PasswordEncoder passwordEncoder;
	
	private AuthenticationManager authenticationManager;
	
	private JwtService jwtService;
	
	public AuthenticationService(CustomerRepo customerRepo , PasswordEncoder passwordEncoder,AuthenticationManager authenticationManager,JwtService jwtService)
	{
		this.customerRepo=customerRepo;
		this.jwtService=jwtService;
		this.passwordEncoder=passwordEncoder;
		this.authenticationManager=authenticationManager;
	}
	
	public AuthenticationResponse register(CustomerDTO customerDTO)
	{
		Customer customer = Customer.builder().name(customerDTO.getName())
		.dob(customerDTO.getDob())
		.password(passwordEncoder.encode(customerDTO.getPassword()))
		.gender(customerDTO.getGender())
		.address(customerDTO.getAddress())
		.mobileNo(customerDTO.getMobileNo())
		.emailId(customerDTO.getEmailId())
		.role(Role.CUSTOMER)
		.build();
		
		customerRepo.save(customer);
		
		String jwtToken = jwtService.generateToken(customer);
		
		return AuthenticationResponse.builder().
				token(jwtToken) .build();
		
		
	}

	public AuthenticationResponse authenticate(LoginCredentials loginReq) throws CustomerNotFoundException
	{
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				loginReq.getUsername(),loginReq.getPassword()));
		
		Optional<Customer> optionalCustomer = customerRepo.findByEmailId(loginReq.getUsername());
		
		if(optionalCustomer.isPresent())
		{
			Customer customer = optionalCustomer.get();
			String jwtToken = jwtService.generateToken(customer);
			
			return AuthenticationResponse.builder().
					token(jwtToken) .build();
		}
		else
		{
			throw new CustomerNotFoundException("Check your Credentials");
		}
		
	}
}


package com.example.demo.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.config.JwtService;
import com.example.demo.dto.CustomerDTO;
import com.example.demo.entity.Customer;
import com.example.demo.entity.LoginCredentials;
import com.example.demo.entity.Role;
import com.example.demo.entity.UserAddress;
import com.example.demo.exceptions.CustomerNotFoundException;
import com.example.demo.exceptions.ExistingUserException;
import com.example.demo.mapper.CustomerMapper;
import com.example.demo.repository.CustomerRepo;
import com.example.demo.repository.UserAddressRepo;

@Service
public class CustomerService  {
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private UserAddressRepo userAddressRepo;
	
	private JwtService jwtServicess;
	
	
	public CustomerService(CustomerRepo customerRepo , UserAddressRepo userAddressRepo ,JwtService jwtServicess) {
		this.customerRepo=customerRepo;
		this.userAddressRepo=userAddressRepo;
		this.jwtServicess=jwtServicess;
	}

	public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) throws ExistingUserException{
		
		Customer customer = CustomerMapper.CUSTOMERMAPPER.mapToCustomer(customerDTO);
		
		UserAddress address = customer.getAddress();
		address = userAddressRepo.save(address);
		customer.setAddress(address);
		customer.setRole(Role.CUSTOMER);
		
//		customer.setPassword(passwordEncoder.encode(customer.getPassword()));
		if(customerRepo.findByEmailId(customerDTO.getEmailId()).isPresent())
		{
			throw new ExistingUserException("User with this"+customerDTO.getEmailId()+"is already present");
		}
				
		Customer savedCustomer =  customerRepo.save(customer);
		
		CustomerDTO savedCustomerDto = CustomerMapper.CUSTOMERMAPPER.mapToCustomerDTO(savedCustomer);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedCustomer.getCustomerId()).toUri();
		
		return ResponseEntity.created(location).body(savedCustomerDto);
		}
	
	

	public ResponseEntity<List<CustomerDTO>> getAllCustomers() throws CustomerNotFoundException
	{
		List<Customer> customers = customerRepo.findAll();
		
		if(customers.isEmpty())
		{
			throw new CustomerNotFoundException("No Customers were added");
		}
		else {
		List<CustomerDTO> customerDTO = customers.stream().map((customer) -> CustomerMapper.CUSTOMERMAPPER.mapToCustomerDTO(customer)).collect(Collectors.toList());
		
		return ResponseEntity.status(HttpStatus.FOUND).body(customerDTO);
		}
	}
	
	
//	public ResponseEntity<String> login(LoginCredentials loginCredentials)
//	{
//		Customer customer = customerRepo.findByName(loginCredentials.getUsername());
//		
//		if(customer!= null && customer.getPassword().equals(loginCredentials.getPassword()))
//		{
//			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Login Successful");
//		}
//		else
//		{
//		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Username or Password");
//		}
//	}
	
	public ResponseEntity<String> deleteCustomer(int id ) throws CustomerNotFoundException
	{
		
		
		Optional<Customer> optionalCustomer = customerRepo.findById(id);
		
		
		
		if(optionalCustomer.isPresent())
		{
			Customer customer = optionalCustomer.get();
			{
				
			UserAddress address = customer.getAddress();
			userAddressRepo.delete(address);
			customerRepo.delete(customer);
			return ResponseEntity.status(HttpStatus.GONE).body("Customer Deleted Successfully");
			}
		
		}
		else
		{
			throw new CustomerNotFoundException("Customer not found with this "+id);
		}
		
		
	}
	
	public AuthenticationResponse authenticate(LoginCredentials loginReq) throws CustomerNotFoundException
	{
		
		
		
		Optional<Customer> optionalCustomer = customerRepo.findByEmailId(loginReq.getUsername());
		
		
		
		if(optionalCustomer.isPresent())
		{
			Customer customer = optionalCustomer.get();
			
			if(customer.getPassword().equals(loginReq.getPassword()))
			{
			String jwtToken = jwtServicess.generateToken(customer);
			
			return AuthenticationResponse.builder().
					token(jwtToken) .build();
		}else
		{
			throw new CustomerNotFoundException("Invalid Password");
		}}
		else
		{
			throw new CustomerNotFoundException("Check your Credentials");
		}
		
	}

	public ResponseEntity<CustomerDTO> getCustomer(int id) throws CustomerNotFoundException {
		
		Optional<Customer> optionalCustomer = customerRepo.findById(id);
		
		if(optionalCustomer.isPresent())
		{
			Customer customer = optionalCustomer.get();
			CustomerDTO customerDTO = CustomerMapper.CUSTOMERMAPPER.mapToCustomerDTO(customer);
			return ResponseEntity.status(HttpStatus.FOUND).body(customerDTO);
		}
		else
		{
			throw new CustomerNotFoundException("Customer with this "+id+" not found");
		}
		
	}
	
	
	public ResponseEntity<CustomerDTO> updateCustomer(int id , CustomerDTO customerDTO) throws CustomerNotFoundException
	{

		Optional<Customer> optionalCustomer = customerRepo.findById(id);
		
		if(optionalCustomer.isPresent())
		{
			Customer customer = optionalCustomer.get();
			customer.setAddress(customerDTO.getAddress());
			customer.setDob(customerDTO.getDob());
			customer.setEmailId(customerDTO.getEmailId());
			customer.setGender(customerDTO.getGender());
			customer.setName(customerDTO.getName());
			customer.setPassword(customerDTO.getPassword());
			customer.setMobileNo(customerDTO.getMobileNo());
			customer.setRole(Role.CUSTOMER);
			
			Customer updatedCustomer = customerRepo.save(customer);
			
			CustomerDTO updatedCustomerDTO = CustomerMapper.CUSTOMERMAPPER.mapToCustomerDTO(updatedCustomer);
			
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(updatedCustomer.getCustomerId()).toUri();
			
			HttpHeaders header = new HttpHeaders();
			header.setLocation(location);
					
			
			
			return ResponseEntity.accepted().headers(header).body(updatedCustomerDTO);
		}
		else
		{
			throw new CustomerNotFoundException("Customer with this "+id+" not found");
		}
		
	
	}
	
	
	}

	



package com.example.demo.mapper;



import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.entity.Customer;

@Mapper
public interface CustomerMapper {
	
	CustomerMapper CUSTOMERMAPPER = Mappers.getMapper(CustomerMapper.class);
	
	CustomerDTO mapToCustomerDTO(Customer customer);
	
	Customer mapToCustomer(CustomerDTO customerDTO);

}

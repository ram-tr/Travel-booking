package com.example.demo.dto;

import java.util.Date;

import com.example.demo.entity.UserAddress;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class CustomerDTO {
	
	private Integer CustomerId;
	private String name;
	private Date dob;
	private String password;
	private String gender;
	private UserAddress address;
	private Long mobileNo;
	private String emailId;

}

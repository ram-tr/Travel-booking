package com.example.demo.dto;

import com.example.demo.entity.UserAddress;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@NoArgsConstructor
@AllArgsConstructor
@Data
public class DriverDTO {
	
	private Integer driverId;
	private String name;
	private UserAddress address;
	private Long mobileNo;
	private String LicenseNo;

}

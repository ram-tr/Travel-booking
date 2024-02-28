package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDTO {
	
	private Integer vehicleId;
	private String vehicleNo;
	private String vehicleName;
	private Integer seatingCapacity;
	private Integer driverId;
	private String vehicleType;
	private Float farePerKm;

}

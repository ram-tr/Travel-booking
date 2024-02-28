package com.example.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.dto.VehicleDTO;
import com.example.demo.entity.Vehicle;

@Mapper
public interface VehicleMapper {
	
	VehicleMapper VEHICLEMAPPER = Mappers.getMapper(VehicleMapper.class);
	
	VehicleDTO mapToVehicleDTO(Vehicle vehicle);
	
	Vehicle mapToVehicle(VehicleDTO vehicleDTO);

}

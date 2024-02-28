package com.example.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.dto.DriverDTO;
import com.example.demo.entity.Driver;

@Mapper
public interface DriverMapper {

	DriverMapper DRIVERMAPPER = Mappers.getMapper(DriverMapper.class);
	
	DriverDTO mapToDriverDTO(Driver driver);
	
	Driver mapToDriver(DriverDTO driverDTO);
}

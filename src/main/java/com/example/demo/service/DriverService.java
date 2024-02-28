package com.example.demo.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.dto.DriverDTO;
import com.example.demo.entity.Driver;
import com.example.demo.entity.UserAddress;
import com.example.demo.exceptions.DriverNotFoundException;
import com.example.demo.mapper.DriverMapper;
import com.example.demo.repository.DriverRepo;
import com.example.demo.repository.UserAddressRepo;

@Service
public class DriverService {
	
	
	@Autowired
	private DriverRepo driverRepo ;
	
	@Autowired
	private UserAddressRepo userAddressRepo;
	
	public DriverService(DriverRepo driverRepo , UserAddressRepo userAddressRepo)
	{
		this.driverRepo=driverRepo;
		this.userAddressRepo=userAddressRepo;
	}
	
	
	public ResponseEntity<DriverDTO> createDriver(DriverDTO driverDTO)
	{
		Driver driver = DriverMapper.DRIVERMAPPER.mapToDriver(driverDTO);
		
		UserAddress address = driver.getAddress();
		address = userAddressRepo.save(address);
		driver.setAddress(address);
		
		Driver savedDriver = driverRepo.save(driver);
		
		DriverDTO savedDriverDTO = DriverMapper.DRIVERMAPPER.mapToDriverDTO(savedDriver);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedDriver.getDriverId()).toUri();
		
		return ResponseEntity.created(location).body(savedDriverDTO);
	}
	
	
	public ResponseEntity<List<DriverDTO>> getAllDrivers() throws DriverNotFoundException
	{
		List<Driver> drivers = driverRepo.findAll();
		
		if(drivers.isEmpty())
		{
			throw new DriverNotFoundException("No Drivers added");
		}
		else {
		List<DriverDTO> driverDTO = drivers.stream().map((driver) -> DriverMapper.DRIVERMAPPER.mapToDriverDTO(driver)).collect(Collectors.toList());
		
		return ResponseEntity.status(HttpStatus.FOUND).body(driverDTO);
		}
	}
	
	
	public ResponseEntity<DriverDTO> getDriverbyId( Integer id) throws DriverNotFoundException
	{
		
		
		Optional<Driver> existingDriver = driverRepo.findById(id);
		
		if(existingDriver.isPresent())
		{
		Driver driver = existingDriver.get();
		
		DriverDTO driverDTO = DriverMapper.DRIVERMAPPER.mapToDriverDTO(driver);
		return ResponseEntity.status(HttpStatus.FOUND).body(driverDTO);
			
		}
		else
		{
			throw new DriverNotFoundException("Driver not found with this "+id);
		}
		
		
	}
	
	
	public ResponseEntity<DriverDTO> updateDriver( Integer id ,DriverDTO driverDTO) throws DriverNotFoundException
	{
		Optional<Driver> driver = driverRepo.findById(id);
		
		
		
		if(id == driverDTO.getDriverId())
		{
			Driver existingDriver = driver.get();
			existingDriver.setAddress(driverDTO.getAddress());
			existingDriver.setName(driverDTO.getName());
			existingDriver.setMobileNo(driverDTO.getMobileNo());
			existingDriver.setlicenseNo(driverDTO.getLicenseNo());
		
		
		Driver updatedDriver = driverRepo.save(existingDriver);
		
		DriverDTO updatedDriverDTO = DriverMapper.DRIVERMAPPER.mapToDriverDTO(updatedDriver);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(updatedDriver.getDriverId()).toUri();
		
		HttpHeaders header = new HttpHeaders();
		header.setLocation(location);
		
		return ResponseEntity.accepted().headers(header).body(updatedDriverDTO);
		}else {
			throw new DriverNotFoundException("Driver not found with this "+id);
		}
		
	}
	
	
	public ResponseEntity<String> deleteDriver(int id) throws DriverNotFoundException
	{
		Optional<Driver> optionalDriver = driverRepo.findById(id);
		
		if(optionalDriver.isPresent())
		{
			Driver driver = optionalDriver.get();
			UserAddress address = driver.getAddress();
			userAddressRepo.delete(address);
			driverRepo.delete(driver);
			return ResponseEntity.status(HttpStatus.GONE).body("Driver Deleted Successfully");
		}
		else
		{
			throw new DriverNotFoundException("Driver not found with this "+id);
		}
		
		
	}
	
}

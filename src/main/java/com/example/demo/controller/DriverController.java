package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.DriverDTO;
import com.example.demo.exceptions.DriverNotFoundException;
import com.example.demo.service.DriverService;

@RestController
@RequestMapping("/drivers")
public class DriverController {
	
	private DriverService driverService;
	
	
	public DriverController(DriverService driverService)
	{
		this.driverService=driverService;
	}
	
	
	@PostMapping
	public ResponseEntity<DriverDTO> newDriver(@RequestBody DriverDTO driverDTO)
	{
		ResponseEntity<DriverDTO> drivDTO = driverService.createDriver(driverDTO);
		return drivDTO;
	}
	
	
	@GetMapping
	public ResponseEntity<List<DriverDTO>> getAllDrivers() throws DriverNotFoundException
	{
		ResponseEntity<List<DriverDTO>> driverDTO = driverService.getAllDrivers();
		
		return driverDTO;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DriverDTO> getDriverbyId(@PathVariable("id") Integer id) throws DriverNotFoundException
	{
		ResponseEntity<DriverDTO> driverDTO = driverService.getDriverbyId(id);
		return driverDTO;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<DriverDTO> updateDriver(@PathVariable Integer id , @RequestBody DriverDTO driverDTO) throws DriverNotFoundException
	{
		ResponseEntity<DriverDTO> drivDTO = driverService.updateDriver(id ,driverDTO);
		return drivDTO;
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteDriver(@PathVariable int id) throws DriverNotFoundException
	{
		ResponseEntity<String> deleted = driverService.deleteDriver(id);
		return deleted;
	}
}

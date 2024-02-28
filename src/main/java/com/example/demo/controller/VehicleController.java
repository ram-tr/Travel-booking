	package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.VehicleDTO;
import com.example.demo.exceptions.VehicleNotFoundException;
import com.example.demo.service.VehicleService;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {
	
	@Autowired
	private VehicleService vehicleService;
	
	public VehicleController(VehicleService vehicleService)
	{
		this.vehicleService=vehicleService;
	}

	@PostMapping
	public ResponseEntity<VehicleDTO> createVehicle(@RequestBody VehicleDTO vehicleDTO)
	{
		ResponseEntity<VehicleDTO> newVehicleDTO = vehicleService.createVehicle(vehicleDTO);
		return newVehicleDTO;
	}
	
	@GetMapping
	public ResponseEntity<List<VehicleDTO>> getAllVehicles() throws VehicleNotFoundException
	{
		ResponseEntity<List<VehicleDTO>> vehicleDTO = vehicleService.getAllVehicles();
		
		return vehicleDTO;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<VehicleDTO> getVehicleById(@PathVariable int id) throws VehicleNotFoundException
	{
		ResponseEntity<VehicleDTO> vehicleDTO = vehicleService.getVehicleById(id);
		return vehicleDTO;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<VehicleDTO> updateVehicle(@PathVariable Integer id , @RequestBody VehicleDTO vehicleDTO) throws VehicleNotFoundException
	{
		ResponseEntity<VehicleDTO> updatedVehicle = vehicleService.updateVehicle(id, vehicleDTO);
		return updatedVehicle;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteVehicle(@PathVariable int id) throws VehicleNotFoundException
	{
		ResponseEntity<String> deletedVehicle = vehicleService.deleteVehicle(id);
		return deletedVehicle;
	}
	
	
	

}

package com.example.demo.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.dto.VehicleDTO;
import com.example.demo.entity.Driver;
import com.example.demo.entity.Vehicle;
import com.example.demo.exceptions.VehicleNotFoundException;
import com.example.demo.mapper.VehicleMapper;
import com.example.demo.repository.DriverRepo;
import com.example.demo.repository.VehicleRepo;

@Service
public class VehicleService {
	
	@Autowired
	private VehicleRepo vehicleRepo;
	
	private DriverRepo driverRepo;
	
	public VehicleService(VehicleRepo vehicleRepo, DriverRepo driverRepo)
	{
		this.vehicleRepo=vehicleRepo;
		this.driverRepo=driverRepo;
		
	}
	
	
	public ResponseEntity<VehicleDTO> createVehicle(VehicleDTO vehicleDTO)
	{
		Vehicle vehicle = VehicleMapper.VEHICLEMAPPER.mapToVehicle(vehicleDTO);
		
		List<Driver> drivers =driverRepo.findAll();
		
		List<Integer> driverIds = drivers.stream().map(Driver::getDriverId).collect(Collectors.toList());
		
		if(!driverIds.isEmpty())
		{
		Random random = new Random();
		int index =random.nextInt(driverIds.size());
		
		Integer driverId = driverIds.get(index);
		
		vehicle.setDriverId(driverId);
		
		}
		else
		{
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}
		
		
		Vehicle savedVehicle = vehicleRepo.save(vehicle);
		
		VehicleDTO savedVehicleDTO = VehicleMapper.VEHICLEMAPPER.mapToVehicleDTO(savedVehicle);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
						.buildAndExpand(savedVehicle.getVehicleId()).toUri();
		
		
		return ResponseEntity.created(location).body(savedVehicleDTO);
		
	}


	public ResponseEntity<List<VehicleDTO>> getAllVehicles() throws VehicleNotFoundException {
		
		List<Vehicle> vehicles = vehicleRepo.findAll();
		
		if(vehicles.isEmpty())
		{
			throw new VehicleNotFoundException("No Vehicles added");
		}else {
			List<VehicleDTO> vehicleDTO = vehicles.stream().map((vehicle) -> VehicleMapper.VEHICLEMAPPER.mapToVehicleDTO(vehicle)).collect(Collectors.toList());
			return ResponseEntity.status(HttpStatus.FOUND).body(vehicleDTO);
		}
		
	}
	
	public ResponseEntity<VehicleDTO> getVehicleById(int id) throws VehicleNotFoundException
	{
		Optional<Vehicle>  optionalVehicle= vehicleRepo.findById(id);
		
		if(optionalVehicle.isPresent())
		{
			Vehicle vehicle  = optionalVehicle.get();
			VehicleDTO vehicleDTO  = VehicleMapper.VEHICLEMAPPER.mapToVehicleDTO(vehicle);
			return ResponseEntity.status(HttpStatus.FOUND).body(vehicleDTO);
		}
		else
		{
			throw new VehicleNotFoundException("Vehicle with this "+id+" not found");
		}
	}
	
	
	public ResponseEntity<VehicleDTO> updateVehicle(int id , VehicleDTO vehicleDTO) throws VehicleNotFoundException
	{
		Optional<Vehicle>  optionalVehicle= vehicleRepo.findById(id);
		
		
		
		if(id == vehicleDTO.getVehicleId())
		{
			Vehicle existingVehicle = optionalVehicle.get();
			existingVehicle.setVehicleName(vehicleDTO.getVehicleName());
			existingVehicle.setVehicleNo(vehicleDTO.getVehicleNo());
			existingVehicle.setSeatingCapacity(vehicleDTO.getSeatingCapacity());
			existingVehicle.setDriverId(vehicleDTO.getDriverId());
			existingVehicle.setVehicleType(vehicleDTO.getVehicleType());
			existingVehicle.setFarePerKm(vehicleDTO.getFarePerKm());
			
			Vehicle updatedVehicle = vehicleRepo.save(existingVehicle);
			
			VehicleDTO updatedVehicleDTO = VehicleMapper.VEHICLEMAPPER.mapToVehicleDTO(updatedVehicle);
			
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(updatedVehicle.getVehicleId()).toUri();
			
			HttpHeaders header = new HttpHeaders();
			header.setLocation(location);
					
			
			
			return ResponseEntity.accepted().headers(header).body(updatedVehicleDTO);
	
		}else
		{
			throw new VehicleNotFoundException("Vehicle with this "+id+" not found");
		}
	}
	
	
	public ResponseEntity<String> deleteVehicle(int id) throws VehicleNotFoundException
	{
		Optional<Vehicle>  optionalVehicle= vehicleRepo.findById(id);
		
		
		if(optionalVehicle.isPresent())
		{
			Vehicle vehicle = optionalVehicle.get();
			vehicleRepo.delete(vehicle);
			return ResponseEntity.status(HttpStatus.GONE).body("Vehicle Deleted Successfully");
		}
		else
		{
			throw new VehicleNotFoundException("Vehicle with this "+id+" not found");
		}
		
	}

}

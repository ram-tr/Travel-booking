package com.example.demo.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.dto.RouteDTO;
import com.example.demo.entity.Route;
import com.example.demo.exceptions.RouteNotFoundException;
import com.example.demo.mapper.RouteMapper;
import com.example.demo.repository.RouteRepo;

@Service
public class RouteService {
	
	
	private RouteRepo routeRepo;
	
	public RouteService(RouteRepo routeRepo)
	{
		this.routeRepo=routeRepo;
	}
	
	public ResponseEntity<RouteDTO> createRoute(RouteDTO routeDTO)
	{
		Route route = RouteMapper.ROUTEMAPPER.mapToRoute(routeDTO);
		
		Route savedRoute = routeRepo.save(route);
		
		RouteDTO savedRouteDTO = RouteMapper.ROUTEMAPPER.mapToRouteDTO(savedRoute);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedRoute.getRouteId()).toUri();
		
		return ResponseEntity.created(location).body(savedRouteDTO);
		
	}
	
	public ResponseEntity<List<RouteDTO>> getAllRoute() throws RouteNotFoundException
	{
		List<Route> routes = routeRepo.findAll();
		
		if(routes.isEmpty())
		{
			throw new RouteNotFoundException("No Drivers added");
		}
		else
		{
		 List<RouteDTO> routeDTO = routes.stream().map((route) -> RouteMapper.ROUTEMAPPER.mapToRouteDTO(route)).collect(Collectors.toList());
		 return ResponseEntity.status(HttpStatus.FOUND).body( routeDTO);
		}
	}
	
	public ResponseEntity<RouteDTO> getRoute(int id) throws RouteNotFoundException
	{
		Optional<Route> optionalRoute = routeRepo.findById(id);
		
		if(optionalRoute.isPresent())
		{
			Route route = optionalRoute.get();
			RouteDTO routeDTO = RouteMapper.ROUTEMAPPER.mapToRouteDTO(route);
			return ResponseEntity.status(HttpStatus.FOUND).body(routeDTO);
			
		}
		else {
			throw new RouteNotFoundException("Route with this "+id+" not found");
		}
	}

	
	public ResponseEntity<RouteDTO> updateRoute (int id , RouteDTO routeDTO) throws RouteNotFoundException
	{
		Optional<Route> optionalRoute = routeRepo.findById(id);
		
		if(id == routeDTO.getRouteId())
		{
			Route existingRoute=optionalRoute.get();
			existingRoute.setSource(routeDTO.getSource());
			existingRoute.setDestination(routeDTO.getDestination());
			existingRoute.setDistance(routeDTO.getDistance());
			existingRoute.setDuration(routeDTO.getDuration());
			
			Route updatedRoute = routeRepo.save(existingRoute);
			
			RouteDTO updatedRouteDTO = RouteMapper.ROUTEMAPPER.mapToRouteDTO(updatedRoute);
			

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(updatedRoute.getRouteId()).toUri();
			
			HttpHeaders header = new HttpHeaders();
			header.setLocation(location);
			
			return ResponseEntity.accepted().headers(header).body(updatedRouteDTO); 
		}
		else
		{
			throw new RouteNotFoundException("Route with this "+id+" not found");
		}
	}
	
	public ResponseEntity<String> deleteRoute(int id) throws RouteNotFoundException
	{
		Optional<Route> optionalRoute = routeRepo.findById(id);
		
		if(optionalRoute.isPresent())
		{
			Route route = optionalRoute.get();
			routeRepo.delete(route);
			return ResponseEntity.status(HttpStatus.GONE).body("Route Deleted Successfully");
		}
		else
		{
			throw new RouteNotFoundException("Route with this "+id+" not found");
		}
	}
	
}

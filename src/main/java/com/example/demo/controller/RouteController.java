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

import com.example.demo.dto.RouteDTO;
import com.example.demo.exceptions.RouteNotFoundException;
import com.example.demo.service.RouteService;

@RestController
@RequestMapping("/routes")
public class RouteController {
	
	private RouteService routeService;

	public RouteController(RouteService routeService) {
		this.routeService = routeService;
	}
	
	@PostMapping
	public ResponseEntity<RouteDTO> createRoute(@RequestBody  RouteDTO routeDTO)
	{
		ResponseEntity<RouteDTO> newRouteDTO = routeService.createRoute(routeDTO);
		return newRouteDTO;
	}
	
	@GetMapping
	public ResponseEntity<List<RouteDTO>> getAllRoute() throws RouteNotFoundException{
		
		ResponseEntity<List<RouteDTO>> routeDTO = routeService.getAllRoute();
		return routeDTO;
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RouteDTO> getRoute(@PathVariable int id) throws RouteNotFoundException{
		ResponseEntity<RouteDTO> routeDTO = routeService.getRoute(id);
		return routeDTO;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<RouteDTO> updateRoute (@PathVariable int id , @RequestBody RouteDTO routeDTO) throws RouteNotFoundException{
		
		ResponseEntity<RouteDTO> updatedRouteDTO = routeService.updateRoute(id, routeDTO);
		return updatedRouteDTO;
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteRoute(@PathVariable int id) throws RouteNotFoundException
	{
		ResponseEntity<String> deletedRoute = routeService.deleteRoute(id);
		return deletedRoute; 
	}
	
}

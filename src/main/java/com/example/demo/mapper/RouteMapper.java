package com.example.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.dto.RouteDTO;
import com.example.demo.entity.Route;

@Mapper
public interface RouteMapper {
	
	RouteMapper ROUTEMAPPER = Mappers.getMapper(RouteMapper.class);
	
	RouteDTO mapToRouteDTO(Route route);
	
	Route mapToRoute(RouteDTO routeDTo);

}

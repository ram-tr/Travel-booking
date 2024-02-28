package com.example.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.dto.AdminDTO;
import com.example.demo.entity.Admin;

@Mapper
public interface AdminMapper {
	
	AdminMapper ADMINMAPPER  = Mappers.getMapper(AdminMapper.class);
	
	AdminDTO mapToAdminDTO (Admin admin);
	
	Admin mapToAdmin (AdminDTO adminDTO);
}

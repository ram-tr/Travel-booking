package com.example.demo.service;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.dto.AdminDTO;
import com.example.demo.entity.Admin;
import com.example.demo.entity.Role;
import com.example.demo.exceptions.AdminNotFoundException;
import com.example.demo.exceptions.ExistingUserException;
import com.example.demo.mapper.AdminMapper;
import com.example.demo.repository.AdminRepo;

@Service
public class AdminService {
	
	
	@Autowired
	private AdminRepo adminRepo;
	
	
	public AdminService(AdminRepo adminRepo)
	{
		this.adminRepo=adminRepo;
	}


	
	public ResponseEntity<AdminDTO> createAdmin(AdminDTO adminDTO) throws ExistingUserException
	{
		Admin admin = AdminMapper.ADMINMAPPER.mapToAdmin(adminDTO);
		
		admin.setRole(Role.ADMIN);
		
		if(adminRepo.findByEmailId(adminDTO.getEmailId()).isPresent())
		{
			throw new ExistingUserException("User with this"+adminDTO.getEmailId()+"is already present");
		}
		
		Admin savedAdmin = adminRepo.save(admin);
		
		AdminDTO savedAdminDTO = AdminMapper.ADMINMAPPER.mapToAdminDTO(savedAdmin);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedAdmin.getAdminId()).toUri();
		
		return ResponseEntity.created(location).body(savedAdminDTO);
	}
	
	
	public ResponseEntity<List<AdminDTO>> getAllAdmins() throws AdminNotFoundException
	{
		List<Admin> admins = adminRepo.findAll();
		
		if(admins.isEmpty())
		{
			throw new AdminNotFoundException("No Admin were Added");
		}
		else {
		List<AdminDTO> adminDTO = admins.stream().map((admin) -> AdminMapper.ADMINMAPPER.mapToAdminDTO(admin)).collect(Collectors.toList());
		
		return ResponseEntity.status(HttpStatus.FOUND).body(adminDTO);
		}
	}
	
	


	
}

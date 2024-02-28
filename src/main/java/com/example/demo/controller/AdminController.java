package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AdminDTO;
import com.example.demo.exceptions.AdminNotFoundException;
import com.example.demo.exceptions.ExistingUserException;
import com.example.demo.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	
	@Autowired
	private AdminService adminService;
	
	@PostMapping
	public ResponseEntity<AdminDTO> newAdmin(@RequestBody AdminDTO adminDTO) throws ExistingUserException
	{
		ResponseEntity<AdminDTO> adm = adminService.createAdmin(adminDTO);
		return adm;
	}
	
	@GetMapping("admins")
	public ResponseEntity<List<AdminDTO>> getAllAdmins() throws AdminNotFoundException
	{
		ResponseEntity<List<AdminDTO>> adminDTO = adminService.getAllAdmins();
		return adminDTO;
	}

}

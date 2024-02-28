package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.entity.LoginCredentials;
import com.example.demo.exceptions.CustomerNotFoundException;
import com.example.demo.service.AuthenticationResponse;
import com.example.demo.service.AuthenticationService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/security")
public class AuthenticationController {
	
	
	private AuthenticationService authenticationService;
	
	public AuthenticationController (AuthenticationService authenticationService)
	{
		this.authenticationService=authenticationService;
	}

	@PostMapping("/register")
	  public ResponseEntity<AuthenticationResponse> register(
	      @RequestBody CustomerDTO customerDTO
	  ) {
	    return ResponseEntity.ok(authenticationService.register(customerDTO));
	  }
	  
	  @PostMapping("/authenticate")
	  public ResponseEntity<AuthenticationResponse> authenticate(
	      @RequestBody LoginCredentials loginCredentials
	  ) throws CustomerNotFoundException {
	    return ResponseEntity.accepted().body(authenticationService.authenticate(loginCredentials));
	  }
}

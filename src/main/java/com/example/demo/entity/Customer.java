package com.example.demo.entity;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Builder
public class Customer implements UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customerId;
	
	@Size(min = 3 , message = "Name must be more than 2 letters")
	private String name;
	
	@Past(message = "Birthdate should be in the past")
	private Date dob;
	
	
	private String password;
	
	private String gender;
	
	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name="address_id" , referencedColumnName = "id")
	private UserAddress  address;
	
	
	@Digits(integer = 10 , fraction = 0)
	@Min(value = 6000000000L , message = "Enter Valid phone number , Phone number must be 10 digits")
	@Max(value = 9999999999L , message = "Enter Valid phone number , Phone number must be 10 digits")
	private long mobileNo;
	
	@Email(message = "Enter Valid E-mail id")
	private String emailId;
	
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	
	
	public Customer() {
		
	}



	public Customer(Integer customerId, String name, Date dob,String password,String gender, UserAddress address,long mobileNo, String emailId, Role role) 
	{
		this.customerId = customerId;
		this.name = name;
		this.dob = dob;
		this.password = password;
		this.gender = gender;
		this.address = address;
		this.mobileNo = mobileNo;
		this.emailId = emailId;
		this.role = role;
	}






	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", name=" + name + ", dob=" + dob + ", password=" + password
				+ ", gender=" + gender + ", address=" + address + ", mobileNo=" + mobileNo + ", emailId=" + emailId
				+ ", role=" + role + "]";
	}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}



	@Override
	public String getUsername() {
		return emailId;
	}



	@Override
	public boolean isAccountNonExpired() {
		return true;
	}



	@Override
	public boolean isAccountNonLocked() {
		return true;
	}



	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}



	@Override
	public boolean isEnabled() {
		return true;
	}
	
	
	@Override
	public String getPassword() {
		return password;
	}
	
	
	

}


package com.example.demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Driver {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer driverId;
	private String name;

	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name="address_id" , referencedColumnName = "id")
	private UserAddress address;
	private Long mobileNo;
	private String licenseNo;
	
	public Driver() {
	}

	public Driver(Integer driverId, String name, UserAddress address, Long mobileNo, String licenseNo) {
		this.driverId = driverId;
		this.name = name;
		this.address = address;
		this.mobileNo = mobileNo;
		this.licenseNo = licenseNo;
	}

	public Integer getDriverId() {
		return driverId;
	}

	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserAddress getAddress() {
		return address;
	}

	public void setAddress(UserAddress address) {
		this.address = address;
	}

	public Long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getlicenseNo() {
		return licenseNo;
	}

	public void setlicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	@Override
	public String toString() {
		return "Driver [driverId=" + driverId + ", name=" + name + ", address=" + address + ", mobileNo=" + mobileNo
				+ ", licenseNo=" + licenseNo + "]";
	}
	
	
	
	
	
}

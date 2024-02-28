package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Vehicle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer vehicleId;
	private String vehicleNo;
	private String vehicleName;
	private Integer seatingCapacity;
	private Integer driverId;
	private String vehicleType;
	private Float farePerKm;
	
	
	public Vehicle() {
		super();
	}


	public Vehicle(Integer vehicleId, String vehicleNo, String vehicleName, Integer seatingCapacity, Integer driverId,
			String vehicleType, Float farePerKm) {
		super();
		this.vehicleId = vehicleId;
		this.vehicleNo = vehicleNo;
		this.vehicleName = vehicleName;
		this.seatingCapacity = seatingCapacity;
		this.driverId = driverId;
		this.vehicleType = vehicleType;
		this.farePerKm = farePerKm;
	}


	public Integer getVehicleId() {
		return vehicleId;
	}


	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}


	public String getVehicleNo() {
		return vehicleNo;
	}


	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}


	public String getVehicleName() {
		return vehicleName;
	}


	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}


	public Integer getSeatingCapacity() {
		return seatingCapacity;
	}


	public void setSeatingCapacity(Integer seatingCapacity) {
		this.seatingCapacity = seatingCapacity;
	}


	public Integer getDriverId() {
		return driverId;
	}


	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}


	public String getVehicleType() {
		return vehicleType;
	}


	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}


	public Float getFarePerKm() {
		return farePerKm;
	}


	public void setFarePerKm(Float farePerKm) {
		this.farePerKm = farePerKm;
	}


	@Override
	public String toString() {
		return "Vehicle [vehicleId=" + vehicleId + ", vehicleNo=" + vehicleNo + ", vehicleName=" + vehicleName
				+ ", seatingCapacity=" + seatingCapacity + ", driverId=" + driverId + ", vehicleType=" + vehicleType
				+ ", farePerKm=" + farePerKm + "]";
	}
	
	
	
	
	
	

}

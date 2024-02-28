package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookingId;
	private Date bookingDate;
	private Date journeyDate;
	private String source;
	private int vehicleId;
	private String destination;
	private String boardingPoint;
	private String dropPoint;
	private int passengers;
	private String bookingStatus;
	private Float fare;
	
	public Booking() {
	}

	public Booking(Integer bookingId, Date bookingDate, Date journeyDate, String source, String destination,
			String boardingPoint, String dropPoint,int vehicleId, int passengers, String bookingStatus, Float fare) {
		this.bookingId = bookingId;
		this.bookingDate = bookingDate;
		this.journeyDate = journeyDate;
		this.source = source;
		this.vehicleId = vehicleId;
		this.destination = destination;
		this.boardingPoint = boardingPoint;
		this.dropPoint = dropPoint;
		this.passengers = passengers;
		this.bookingStatus = bookingStatus;
		this.fare = fare;
	}

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public Date getJourneyDate() {
		return journeyDate;
	}

	public void setJourneyDate(Date journeyDate) {
		this.journeyDate = journeyDate;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getBoardingPoint() {
		return boardingPoint;
	}

	public void setBoardingPoint(String boardingPoint) {
		this.boardingPoint = boardingPoint;
	}

	public String getDropPoint() {
		return dropPoint;
	}

	public void setDropPoint(String dropPoint) {
		this.dropPoint = dropPoint;
	}

	public int getPassengers() {
		return passengers;
	}

	public void setPassengers(int passengers) {
		this.passengers = passengers;
	}

	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public Float getFare() {
		return fare;
	}

	public void setFare(Float fare) {
		this.fare = fare;
	}

	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", bookingDate=" + bookingDate
				+ ", journeyDate=" + journeyDate + ", source=" + source + ", destination=" + destination
				+ ", boardingPoint=" + boardingPoint + ", dropPoint=" + dropPoint + ", passengers=" + passengers
				+ ", bookingStatus=" + bookingStatus + ", Fare=" + fare + "]";
	}

	




	
	
	
	
}

package com.example.demo.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {

	private Integer bookingId;
	private int vehicleId;
	private Date bookingDate;
	private Date journeyDate;
	private String source;
	private String destination;
	private String boardingPoint;
	private String dropPoint;
	private int passengers;
	
	private String bookingStatus;
	
	private Float Fare;
}

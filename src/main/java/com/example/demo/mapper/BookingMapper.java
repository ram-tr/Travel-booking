package com.example.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.dto.BookingDTO;
import com.example.demo.entity.Booking;

@Mapper
public interface BookingMapper {
	
	BookingMapper BOOKINGMAPPER = Mappers.getMapper(BookingMapper.class);
	
	BookingDTO mapToBookingDTO(Booking booking);
	
	Booking mapToBooking(BookingDTO bookingDTO);

}

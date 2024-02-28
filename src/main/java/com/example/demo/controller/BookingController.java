package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.BookingDTO;
import com.example.demo.exceptions.BookingNotFoundException;
import com.example.demo.service.BookingService;

@RestController
@RequestMapping("/bookings")
public class BookingController {

	private BookingService bookingService;

	public BookingController(BookingService bookingService) {
		super();
		this.bookingService = bookingService;
	}
	
	@PostMapping
	public ResponseEntity<BookingDTO> createBooking(@RequestBody BookingDTO bookingDTO)
	{
		ResponseEntity<BookingDTO> newBooking = bookingService.createBooking(bookingDTO);
		return newBooking;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BookingDTO> getBooking(@PathVariable int id) throws BookingNotFoundException
	{
		ResponseEntity<BookingDTO> bookingDTO = bookingService.getBooking(id);
		return bookingDTO;
				
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteBooking(@PathVariable int id) throws BookingNotFoundException
	{
		ResponseEntity<String> canceledBooking = bookingService.deleteBooking(id);
		return canceledBooking;
	}
}

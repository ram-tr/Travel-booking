package com.example.demo.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.dto.BookingDTO;
import com.example.demo.entity.Booking;
import com.example.demo.entity.Route;
import com.example.demo.entity.Vehicle;
import com.example.demo.exceptions.BookingNotFoundException;
import com.example.demo.mapper.BookingMapper;
import com.example.demo.repository.BookingRepo;
import com.example.demo.repository.RouteRepo;
import com.example.demo.repository.VehicleRepo;

@Service
public class BookingService {

	private BookingRepo bookingRepo;
	
	private VehicleRepo vehicleRepo;
	
	private RouteRepo routeRepo;

	public BookingService(BookingRepo bookingRepo , VehicleRepo vehicleRepo , RouteRepo routeRepo) {
		this.bookingRepo = bookingRepo;
		this.vehicleRepo=vehicleRepo;
		this.routeRepo=routeRepo;
	}

	public ResponseEntity<BookingDTO> createBooking(BookingDTO bookingDTO) {
		
		
		Booking booking = BookingMapper.BOOKINGMAPPER.mapToBooking(bookingDTO);

		booking.setBookingStatus("pending");;
		
		Booking savedBooking = bookingRepo.save(booking);

		BookingDTO savedBookingDTO = BookingMapper.BOOKINGMAPPER.mapToBookingDTO(savedBooking);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedBooking.getBookingId()).toUri();

		return ResponseEntity.created(location).body(savedBookingDTO);
	}

	public ResponseEntity<BookingDTO> getBooking(int id) throws BookingNotFoundException {
		Optional<Booking> optionalBooking = bookingRepo.findById(id);

		if (optionalBooking.isPresent()) {
			Booking booking = optionalBooking.get();
			
			
			Float farePrice = calculatePrice(booking);
			
			booking.setFare(farePrice);
			
			BookingDTO bookingDTO = BookingMapper.BOOKINGMAPPER.mapToBookingDTO(booking);
			return ResponseEntity.status(HttpStatus.FOUND).body(bookingDTO);
		} else {
			throw new BookingNotFoundException("Booking not found with this "+id);
		}
	}

	public ResponseEntity<String> deleteBooking(int id) throws BookingNotFoundException {
		Optional<Booking> optionalBooking = bookingRepo.findById(id);

		if (optionalBooking.isPresent()) {
			Booking booking = optionalBooking.get();
			bookingRepo.delete(booking);
			return ResponseEntity.status(HttpStatus.GONE).body("Booking Cancelled Successfully");
		}
		else
		{
			throw new BookingNotFoundException("Booking not found with this "+id);
		}

	}
	
	
	
	public Float calculatePrice(Booking booking)
	{
		
		List<Route> route = routeRepo.findAll();
		
 		int passengers = booking.getPassengers();
 		Float distance = 0f;
		Float price =0f;
 		
		List<Vehicle> vehicles = vehicleRepo.findAll();
		List<Integer> vehiclesId =  vehicles.stream().map(Vehicle :: getVehicleId ).collect(Collectors.toList()); 
		if(!vehiclesId.isEmpty())
		{
		Random random = new Random();
		int index =random.nextInt(vehiclesId.size());
		
		Integer vehicleId = vehiclesId.get(index);
		
		booking.setVehicleId(vehicleId);
		booking.setBookingStatus("completed");
		
		int id = booking.getVehicleId();
		
		
		
		for (Route everyRoute : route) {
			
			if((booking.getSource().toLowerCase()).matches(everyRoute.getSource().toLowerCase()) &&
			(booking.getDestination().toLowerCase()).matches(everyRoute.getDestination().toLowerCase()))
			{
				distance = everyRoute.getDistance();
		}
		}
		
		if(id != 0)
		{
			Optional<Vehicle> optionalVehicle = vehicleRepo.findById(id);
			Vehicle vehicle = optionalVehicle.get();
			Float farekm = vehicle.getFarePerKm();
			
			 price = passengers * (distance * farekm);
			
		}
		
		}
		else
		{
			booking.setBookingStatus("pending");
		}
		
		return price;
	}
	
	
}

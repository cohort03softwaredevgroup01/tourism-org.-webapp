package com.tour.webapp;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HotelController {
	
	@Autowired
	private HotelService service;
	
	@GetMapping("/hotels")
	public List<Hotels> list() {
		
		return service.getListHotels();
	}
	
	@PostMapping("/hotels")
	public void add(@RequestBody Hotels addHotel) {
	    service.save(addHotel);
	}
	@PutMapping("/hotels/{id}")
	public ResponseEntity<?> update(@RequestBody Hotels updateHotel, @PathVariable Integer id) {
	    try {
	         service.getHotel(id);
	        service.save(updateHotel);
	        return new ResponseEntity<>(HttpStatus.OK);
	    } catch (NoSuchElementException e) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }      
	}
	
	@DeleteMapping("/hotels/{id}")
	public void delete(@PathVariable Integer id) {
	    service.delete(id);
	}
	
}

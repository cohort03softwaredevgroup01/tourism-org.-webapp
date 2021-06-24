package com.tour.webapp.controllers;
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

import com.tour.webapp.model.Packages;
import com.tour.webapp.services.PackageService;

@RestController
public class PackageController {
	
	@Autowired
	private PackageService service;
	
	@GetMapping("/packages")
	public List<Packages> list() {
		
		return service.getListPackages();
	}
	
	@PostMapping("/packages")
	public void add(@RequestBody Packages addPackage) {
	    service.save(addPackage);
	}
	
	@PutMapping("/packages/{id}")
	public ResponseEntity<?> update(@RequestBody Packages updatePackage, @PathVariable Integer id) {
	    try {
	         service.getPackage(id);
	        service.save(updatePackage);
	        return new ResponseEntity<>(HttpStatus.OK);
	    } catch (NoSuchElementException e) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }      
	}
	
	@DeleteMapping("/packages/{id}")
	public ResponseEntity<Object> delete(@PathVariable Integer id) {
	    
	    try {
	    	service.delete(id);
	        return new ResponseEntity<>(HttpStatus.OK);
	    } catch (NoSuchElementException e) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }    
	}

}

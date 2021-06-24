package com.tour.webapp.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tour.webapp.dao.PackagesRepo;
import com.tour.webapp.model.Packages;

@Service
@Transactional
public class PackageService {
	@Autowired
	private PackagesRepo repo;
	
	public List<Packages> getListPackages(){
		return repo.findAll();
	}	
	public void save(Packages packages) {
		repo.save(packages);
	
	}
	
	public Packages getPackage(int id) {
		return repo.findById(id).get();
		
		
	}
	
	public void delete(int id) {
		repo.deleteById(id);
	}
		
}
	

	



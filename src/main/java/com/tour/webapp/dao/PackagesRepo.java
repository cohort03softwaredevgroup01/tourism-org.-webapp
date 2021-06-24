package com.tour.webapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tour.webapp.model.Packages;

public interface PackagesRepo extends JpaRepository<Packages,Integer> {

}

package com.tour.webapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tour.webapp.model.Hotels;

public interface HotelRepo extends JpaRepository<Hotels,Integer> {

}

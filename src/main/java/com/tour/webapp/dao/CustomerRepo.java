package com.tour.webapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tour.webapp.model.Customers;

public interface CustomerRepo extends JpaRepository <Customers ,Integer> {

}

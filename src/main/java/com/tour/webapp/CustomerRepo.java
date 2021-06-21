package com.tour.webapp;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository <Customers ,Integer> {

}

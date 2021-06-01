package com.assignment3.assignment3.car;

import com.assignment3.assignment3.car.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car,Long> {
    Page<Car> findAllByOfficeId(Long id, Pageable pageable);
}

package com.assignment3.assignment3.car;

import com.assignment3.assignment3.car.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
}

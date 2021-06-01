package com.assignment3.assignment3.car.mapper;

import com.assignment3.assignment3.car.dto.CarDisplayDto;
import com.assignment3.assignment3.car.dto.CarSimpleDto;
import com.assignment3.assignment3.car.model.Car;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarMapper {
    CarDisplayDto carDisplayFromCar(Car car);
    CarSimpleDto carSimpleFromCar(Car car);
}

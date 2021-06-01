package com.assignment3.assignment3.car;

import com.assignment3.assignment3.car.dto.CarDisplayDto;
import com.assignment3.assignment3.car.dto.CarRequestDto;
import com.assignment3.assignment3.car.mapper.CarMapper;
import com.assignment3.assignment3.car.model.Car;
import com.assignment3.assignment3.office.OfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarService {

    private final ManufacturerService manufacturerService;
    private final OfficeService officeService;
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public CarDisplayDto save(CarRequestDto carRequestDto) {
        var manufacturer = manufacturerService
                .findById(carRequestDto.getManufacturerId());
        var office = officeService.findById(carRequestDto.getOfficeId());
        var car = carRepository.save(
                Car.builder()
                        .description(carRequestDto.getDescription())
                        .model(carRequestDto.getModel())
                        .manufacturer(manufacturer)
                        .office(office)
                        .isAvailable(true)
                        .build()
        );
        return carMapper.carDisplayFromCar(car);
    }

    public CarDisplayDto update(Long id, CarRequestDto requestDto) {
        var car = findById(id);
        var manufacturer = manufacturerService
                .findById(requestDto.getManufacturerId());
        var office = officeService.findById(requestDto.getOfficeId());
        car.setDescription(requestDto.getDescription());
        car.setModel(requestDto.getModel());
        car.setManufacturer(manufacturer);
        car.setOffice(office);
        return carMapper.carDisplayFromCar(
                carRepository.save(car)
        );
    }

    public Car findById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Couldn't find car with given id"
                ));
    }

    public void delete(Long id) {
        carRepository.deleteById(id);
    }

    public Page<CarDisplayDto> findAllByOffice(Long id, Integer page, Integer carsPerPage) {
        var cars = carRepository.findAllByOfficeId(id,
                PageRequest.of(page, carsPerPage));
        return cars.map(carMapper::carDisplayFromCar);
    }

    public Page<CarDisplayDto> findAll(Integer page, Integer carsPerPage) {
        var cars = carRepository.findAll(PageRequest.of(page, carsPerPage));
        return cars.map(carMapper::carDisplayFromCar);
    }
}

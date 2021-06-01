package com.assignment3.assignment3.car;

import com.assignment3.assignment3.car.dto.ManufacturerDisplayDto;
import com.assignment3.assignment3.car.dto.ManufacturerRequestDto;
import com.assignment3.assignment3.car.mapper.ManufacturerMapper;
import com.assignment3.assignment3.car.model.Manufacturer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ManufacturerService {

    private final ManufacturerMapper manufacturerMapper;
    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerDisplayDto save(ManufacturerRequestDto requestDto) {
        var manufacturer = manufacturerRepository
                .save(manufacturerMapper.manufacturerFromRequest(requestDto));
        return manufacturerMapper.manufacturerDisplayFromManufacturer(manufacturer);
    }

    public ManufacturerDisplayDto update(Long id, ManufacturerRequestDto requestDto) {
        var manufacturer = findById(id);
        manufacturer.setName(requestDto.getName());
        return manufacturerMapper.manufacturerDisplayFromManufacturer(
                manufacturerRepository.save(manufacturer)
        );
    }

    public void delete(Long id) {
        manufacturerRepository.deleteById(id);
    }

    public Manufacturer findById(Long id) {
        return manufacturerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Couldn't find manufacturer with given id"));
    }

    public List<ManufacturerDisplayDto> findAll() {
        return manufacturerRepository.findAll()
                .stream().map(manufacturerMapper::manufacturerDisplayFromManufacturer)
                .collect(Collectors.toList());
    }
}

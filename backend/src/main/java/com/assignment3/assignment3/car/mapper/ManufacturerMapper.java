package com.assignment3.assignment3.car.mapper;

import com.assignment3.assignment3.car.dto.ManufacturerDisplayDto;
import com.assignment3.assignment3.car.dto.ManufacturerRequestDto;
import com.assignment3.assignment3.car.model.Manufacturer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ManufacturerMapper {
    ManufacturerDisplayDto manufacturerDisplayFromManufacturer(Manufacturer manufacturer);
    Manufacturer manufacturerFromRequest(ManufacturerRequestDto manufacturerRequestDto);
}

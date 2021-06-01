package com.assignment3.assignment3.car.dto;

import com.assignment3.assignment3.office.dto.OfficeDisplayDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarDisplayDto {
    private Long id;
    private String description;
    private ManufacturerDisplayDto manufacturer;
    private OfficeDisplayDto office;
    private String model;
    private Boolean isAvailable;
}

package com.assignment3.assignment3.car.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarSimpleDto {
    private Long id;
    private String description;
    private ManufacturerDisplayDto manufacturer;
    private String model;
    private Boolean isAvailable;
}

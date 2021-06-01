package com.assignment3.assignment3.car.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarRequestDto {
    private String description;
    private Long manufacturerId;
    private Long officeId;
    private String model;
}

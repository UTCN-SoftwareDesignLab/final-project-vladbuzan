package com.assignment3.assignment3.office.dto;

import com.assignment3.assignment3.car.dto.CarDisplayDto;
import com.assignment3.assignment3.car.dto.CarSimpleDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OfficeDisplayDto {
    private Long id;
    private String name;
    private Double latitude;
    private Double longitude;
    private List<CarSimpleDto> cars;
}

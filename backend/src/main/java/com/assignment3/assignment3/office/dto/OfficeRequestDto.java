package com.assignment3.assignment3.office.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OfficeRequestDto {
    private String name;
    private Double latitude;
    private Double longitude;
}

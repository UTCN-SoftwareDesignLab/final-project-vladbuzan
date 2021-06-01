package com.assignment3.assignment3.rental.dto;

import com.assignment3.assignment3.car.dto.CarDisplayDto;
import com.assignment3.assignment3.user.dto.UserDisplayDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentalDisplayDto {
    private Long id;
    private UserDisplayDto customer;
    private CarDisplayDto car;
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime rentalStart;
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime rentalEnd;
    private String status;
}

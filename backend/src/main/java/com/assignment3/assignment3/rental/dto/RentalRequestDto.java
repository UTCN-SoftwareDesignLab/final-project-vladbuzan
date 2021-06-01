package com.assignment3.assignment3.rental.dto;

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
public class RentalRequestDto {
    private Long userId;
    private Long carId;
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime rentalStart;
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime rentalEnd;
}

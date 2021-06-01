package com.assignment3.assignment3.rental.mapper;

import com.assignment3.assignment3.rental.dto.RentalDisplayDto;
import com.assignment3.assignment3.rental.model.Rental;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface RentalMapper {
    RentalDisplayDto rentalDisplayFromRental(Rental rental);
}

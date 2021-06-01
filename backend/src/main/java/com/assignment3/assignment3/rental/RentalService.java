package com.assignment3.assignment3.rental;

import com.assignment3.assignment3.car.CarService;
import com.assignment3.assignment3.rental.dto.RentalDisplayDto;
import com.assignment3.assignment3.rental.dto.RentalRequestDto;
import com.assignment3.assignment3.rental.mapper.RentalMapper;
import com.assignment3.assignment3.rental.model.EStatus;
import com.assignment3.assignment3.rental.model.Rental;
import com.assignment3.assignment3.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final UserService userService;
    private final CarService carService;
    private final RentalRepository rentalRepository;
    private final RentalMapper rentalMapper;
    private final MailService mailService;

    public RentalDisplayDto save(RentalRequestDto requestDto) {
        var user = userService.findById(requestDto.getUserId());
        var car = carService.findById(requestDto.getCarId());
        if(!isAvailable(car.getId(), requestDto.getRentalStart(), requestDto.getRentalEnd())){
            throw new RuntimeException("Car unavailable at rental date");
        }
        var rental = rentalRepository.save(
                Rental.builder()
                        .car(car)
                        .customer(user)
                        .rentalStart(requestDto.getRentalStart())
                        .rentalEnd(requestDto.getRentalEnd())
                        .status(EStatus.PENDING)
                        .build()
        );
        mailService.sendMail(rental);
        return rentalMapper.rentalDisplayFromRental(rental);
    }

    public Page<RentalDisplayDto> findByCarId(Long id, int page, int rentalsPerPage) {
        return rentalRepository.findAllByCarId(id, PageRequest.of(page, rentalsPerPage))
                .map(rentalMapper::rentalDisplayFromRental);
    }

    public Page<RentalDisplayDto> findAll(Integer page, Integer rentalsPerPage) {
        return rentalRepository.findAllByStatusOrStatus(EStatus.IN_USE, EStatus.PENDING,
                PageRequest.of(page, rentalsPerPage)).map(rentalMapper::rentalDisplayFromRental);
    }

    public Boolean isAvailable(Long id, LocalDateTime start, LocalDateTime end) {
        return((rentalRepository.countByCarIdAndRentalStartLessThanAndRentalEndGreaterThan(id, start, start) == 0)
        &&(rentalRepository.countByCarIdAndRentalStartLessThanAndRentalEndGreaterThan(id, end, end) == 0)
        &&(rentalRepository.countByCarIdAndRentalStartGreaterThanAndRentalEndLessThan(id, start, end) == 0));
    }

    public RentalDisplayDto changeStatus(Long id) {
        var rental = rentalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Couldn't find rental with given id"));
        if(rental.getStatus() == EStatus.PENDING) {
            rental.setStatus(EStatus.IN_USE);
            rental =  rentalRepository.save(rental);
            return rentalMapper.rentalDisplayFromRental(rental);
        }
        if(rental.getStatus() == EStatus.IN_USE) {
            rental.setStatus(EStatus.RETURNED);
            rental =  rentalRepository.save(rental);
            return rentalMapper.rentalDisplayFromRental(rental);
        }
        if(rental.getStatus() == EStatus.RETURNED) {
            throw new RuntimeException("Car is already returned");
        }
        return rentalMapper.rentalDisplayFromRental(rental);
    }
}

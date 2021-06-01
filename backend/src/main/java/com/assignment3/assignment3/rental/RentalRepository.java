package com.assignment3.assignment3.rental;

import com.assignment3.assignment3.rental.model.EStatus;
import com.assignment3.assignment3.rental.model.Rental;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    long countByCarIdAndRentalStartLessThanAndRentalEndGreaterThan(Long id, LocalDateTime start, LocalDateTime start2);
    long countByCarIdAndRentalStartGreaterThanAndRentalEndLessThan(Long id, LocalDateTime start, LocalDateTime end);
    Page<Rental> findAllByCarId(Long id, Pageable pageable);
    Page<Rental> findAllByStatusOrStatus(EStatus status1, EStatus status2, Pageable pageable);
}

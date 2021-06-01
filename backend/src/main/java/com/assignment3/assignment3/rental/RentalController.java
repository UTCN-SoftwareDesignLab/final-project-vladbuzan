package com.assignment3.assignment3.rental;

import com.assignment3.assignment3.ControllerBase;
import com.assignment3.assignment3.rental.dto.RentalRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.assignment3.assignment3.UrlMapping.RENTAL;

@RestController
@RequestMapping(RENTAL)
@RequiredArgsConstructor
public class RentalController extends ControllerBase {

    private final RentalService rentalService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody RentalRequestDto requestDto) {
        try {
            var rental = rentalService.save(requestDto);
            return ok(rental);
        } catch (Exception ex) {
            return badRequest(ex.getMessage());
        }
    }

    @GetMapping("/byCar/{id}")
    public ResponseEntity<?> findByCarId(@PathVariable Long id, @RequestParam Integer page,
                                         @RequestParam Integer rentalsPerPage) {
        try {
            var rentals = rentalService.findByCarId(id, page, rentalsPerPage);
            return ok(rentals);
        } catch (Exception ex) {
            return badRequest(ex.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> findAll(@RequestParam Integer page,
                                     @RequestParam Integer rentalsPerPage) {
        try {
            var rentals = rentalService.findAll(page, rentalsPerPage);
            return ok(rentals);
        } catch (Exception ex) {
            return badRequest(ex.getMessage());
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity<?> changeStatus(@PathVariable Long id) {
        try {
            var rental = rentalService.changeStatus(id);
            return ok(rental);
        } catch(Exception ex) {
            return badRequest(ex.getMessage());
        }
    }
}

package com.assignment3.assignment3.car;

import com.assignment3.assignment3.ControllerBase;
import com.assignment3.assignment3.car.dto.CarRequestDto;
import com.assignment3.assignment3.security.dto.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.assignment3.assignment3.UrlMapping.CAR;

@RestController
@RequestMapping(CAR)
@RequiredArgsConstructor
public class CarController extends ControllerBase {

    private final CarService carService;

    @GetMapping("{id}")
    public ResponseEntity<?> findAllByOffice(@PathVariable Long id, @RequestParam Integer page,
                                             @RequestParam Integer carsPerPage) {
        try {
            var cars = carService.findAllByOffice(id, page, carsPerPage);
            return ok(cars);
        } catch (Exception ex) {
            return badRequest(ex.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> findAll(@RequestParam Integer page,
                                     @RequestParam Integer carsPerPage) {
        try {
            var cars = carService.findAll(page, carsPerPage);
            return ok(cars);
        } catch (Exception ex) {
            return badRequest(ex.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody CarRequestDto requestDto) {
        try {
            var car = carService.save(requestDto);
            return ok(car);
        } catch (Exception ex) {
            return badRequest(ex.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody CarRequestDto requestDto) {
        try {
            var car = carService.update(id, requestDto);
            return ok(car);
        } catch (Exception ex) {
            return badRequest(ex.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            carService.delete(id);
            return ok(new MessageResponse(
                    "Car deleted successfully."
            ));
        } catch (Exception ex) {
            return badRequest(ex.getMessage());
        }
    }
    
}

package com.assignment3.assignment3.car;


import com.assignment3.assignment3.ControllerBase;
import com.assignment3.assignment3.car.dto.ManufacturerRequestDto;
import com.assignment3.assignment3.security.dto.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.assignment3.assignment3.UrlMapping.MANUFACTURER;

@RestController
@RequestMapping(MANUFACTURER)
@RequiredArgsConstructor
public class ManufacturerController extends ControllerBase {

    private final ManufacturerService manufacturerService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        try {
            var manufacturers = manufacturerService.findAll();
            return ok(manufacturers);
        } catch (Exception ex) {
            return badRequest(ex.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ManufacturerRequestDto requestDto) {
        try {
            var manufacturer = manufacturerService.save(requestDto);
            return ok(manufacturer);
        } catch (Exception ex) {
            return badRequest(ex.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody ManufacturerRequestDto requestDto) {
        try {
            var manufacturer = manufacturerService.update(id, requestDto);
            return ok(manufacturer);
        } catch (Exception ex) {
            return badRequest(ex.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            manufacturerService.delete(id);
            return ok(new MessageResponse("Manufacturer removed successfully"));
        } catch (Exception ex) {
            return badRequest(ex.getMessage());
        }
    }

}

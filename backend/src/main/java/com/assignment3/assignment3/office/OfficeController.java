package com.assignment3.assignment3.office;

import com.assignment3.assignment3.ControllerBase;
import com.assignment3.assignment3.office.dto.OfficeRequestDto;
import com.assignment3.assignment3.security.dto.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.assignment3.assignment3.UrlMapping.OFFICE;

@RestController
@RequiredArgsConstructor
@RequestMapping(OFFICE)
public class OfficeController extends ControllerBase {

    private final OfficeService officeService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        try {
            var offices = officeService.findAll();
            return ok(offices);
        } catch (Exception ex) {
            return badRequest(ex.getMessage());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        try {
            var office = officeService.findByIdDto(id);
            return ok(office);
        } catch (Exception ex) {
            return badRequest(ex.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            officeService.delete(id);
            return ok(new MessageResponse(
                    "Office removed successfully"
            ));
        } catch (Exception ex) {
            return badRequest(ex.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody OfficeRequestDto requestDto) {
        try {
            var office = officeService.update(id, requestDto);
            return ok(office);
        } catch (Exception ex) {
            return badRequest(ex.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody OfficeRequestDto requestDto) {
        try {
            var office = officeService.save(requestDto);
            return ok(office);
        } catch (Exception ex) {
            return badRequest(ex.getMessage());
        }
    }
}

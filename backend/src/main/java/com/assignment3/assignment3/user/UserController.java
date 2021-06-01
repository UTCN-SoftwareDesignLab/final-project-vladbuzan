package com.assignment3.assignment3.user;

import com.assignment3.assignment3.ControllerBase;
import com.assignment3.assignment3.security.dto.MessageResponse;
import com.assignment3.assignment3.security.dto.UpdateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.assignment3.assignment3.UrlMapping.USER;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController extends ControllerBase {
    private final UserService userService;


    @PutMapping("{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UpdateUserRequest request) {
        try {
            userService.updateUser(request, id);
            return ok("User updated successfully");
        } catch (Exception ex) {
            return badRequest(ex.getMessage());
        }
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> removeUser(@PathVariable Long id) {
        try {
            userService.removeUser(id);
            return ResponseEntity.ok(
                    new MessageResponse(
                            "User removed successfully"
                    ));
        } catch (Exception ex) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse(ex.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> findAll(@RequestParam Integer page,
                                     @RequestParam Integer usersPerPage) {
        try {
            var users = userService.findAll(page, usersPerPage);
            return ok(users);
        } catch (Exception ex) {
            return badRequest(ex.getMessage());
        }
    }
}

package com.assignment3.assignment3;

import org.springframework.http.ResponseEntity;

public class ControllerBase {
    public ResponseEntity<?> ok(Object response) {
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> badRequest(String message) {
        return ResponseEntity.badRequest().body(message);
    }
}

package com.grigoriank.taskService.endpoint;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeEndpoint {

    @GetMapping("/tasks")
    public ResponseEntity<String> home() {
        return new ResponseEntity<>("Welcome to the task service", HttpStatus.OK);
    }
}

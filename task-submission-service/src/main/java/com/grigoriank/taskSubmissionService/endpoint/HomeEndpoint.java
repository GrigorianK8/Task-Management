package com.grigoriank.taskSubmissionService.endpoint;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeEndpoint {

    @GetMapping("/submission")
    public ResponseEntity<String> home() {
        return new ResponseEntity<>("Welcome to the submission service!", HttpStatus.OK);
    }
}

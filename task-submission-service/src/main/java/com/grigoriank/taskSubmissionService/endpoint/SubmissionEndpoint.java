package com.grigoriank.taskSubmissionService.endpoint;

import com.grigoriank.taskSubmissionService.client.UserServiceClient;
import com.grigoriank.taskSubmissionService.dto.UserDTO;
import com.grigoriank.taskSubmissionService.entity.Submission;
import com.grigoriank.taskSubmissionService.service.SubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/submissions")
public class SubmissionEndpoint {

    private final SubmissionService submissionService;
    private final UserServiceClient userServiceClient;

    @PostMapping()
    public ResponseEntity<Submission> submitTask(@RequestParam Long taskId,
                                                 @RequestParam String githubLink,
                                                 @RequestHeader("Authorization") String jwt) throws Exception {

        UserDTO user = userServiceClient.getUserProfile(jwt);
        Submission submission = submissionService.submitTask(taskId, githubLink, user.getId(), jwt);
        return new ResponseEntity<>(submission, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Submission> getSubmissionById(@PathVariable Long id,
                                                        @RequestHeader("Authorization") String jwt) throws Exception {

        UserDTO user = userServiceClient.getUserProfile(jwt);
        Submission submission = submissionService.getTaskSubmissionById(id);
        return new ResponseEntity<>(submission, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Submission>> getAllSubmissions(@RequestHeader("Authorization") String jwt) {
        UserDTO user = userServiceClient.getUserProfile(jwt);
        List<Submission> submissions = submissionService.getAllTaskSubmissions();
        return new ResponseEntity<>(submissions, HttpStatus.OK);
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<Submission>> getTaskSubmissions(@PathVariable Long taskId,
                                                               @RequestHeader("Authorization") String jwt) {

        UserDTO user = userServiceClient.getUserProfile(jwt);
        List<Submission> submissions = submissionService.getTaskSubmissionsByTaskId(taskId);
        return new ResponseEntity<>(submissions, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Submission> acceptOrDeclineSubmission(@PathVariable Long id,
                                                                @RequestParam("status") String status,
                                                                @RequestHeader("Authorization") String jwt)
            throws Exception {

        UserDTO user = userServiceClient.getUserProfile(jwt);
        Submission submission = submissionService.acceptDeclineSubmission(id, status);
        return new ResponseEntity<>(submission, HttpStatus.OK);
    }
}

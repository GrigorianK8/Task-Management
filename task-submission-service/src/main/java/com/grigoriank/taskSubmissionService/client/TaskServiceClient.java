package com.grigoriank.taskSubmissionService.client;

import com.grigoriank.taskSubmissionService.dto.TaskDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "TASK-SERVICE", url = "http://localhost:8081")
public interface TaskServiceClient {

    @GetMapping("/api/tasks/{id}")
    TaskDTO getTaskById(@PathVariable Long id,
                        @RequestHeader("Authorization") String jwt) throws Exception;

    @PutMapping("/api/tasks/{id}/complete")
    TaskDTO completeTask(@PathVariable Long id) throws Exception;
}

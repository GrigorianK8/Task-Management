package com.grigoriank.taskService.endpoint;

import com.grigoriank.taskService.client.UserServiceClient;
import com.grigoriank.taskService.dto.UserDTO;
import com.grigoriank.taskService.entity.Task;
import com.grigoriank.taskService.entity.TaskStatus;
import com.grigoriank.taskService.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
public class TaskEndpoint {

    private final TaskService taskService;
    private final UserServiceClient userServiceClient;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task,
                                           @RequestHeader("Authorization") String jwt) throws Exception {

        UserDTO user = userServiceClient.getUserProfile(jwt);
        Task createdTask = taskService.createTask(task, user.getRole());
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id,
                                            @RequestHeader("Authorization") String jwt) throws Exception {

        UserDTO user = userServiceClient.getUserProfile(jwt);
        Task task = taskService.getTaskById(id);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Task>> getAssignedUsersTask(@RequestParam(required = false) TaskStatus status,
                                                           @RequestHeader("Authorization") String jwt) {

        UserDTO user = userServiceClient.getUserProfile(jwt);
        List<Task> tasks = taskService.assignedUsersTask(user.getId(), status);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTask(@RequestParam(required = false) TaskStatus status,
                                                 @RequestHeader("Authorization") String jwt) {

        UserDTO user = userServiceClient.getUserProfile(jwt);
        List<Task> tasks = taskService.getAllTask(status);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PutMapping("/{id}/user/{userId}/assigned")
    public ResponseEntity<Task> assignedTaskToUser(@PathVariable Long id, @PathVariable Long userId,
                                                   @RequestHeader("Authorization") String jwt) throws Exception {

        UserDTO user = userServiceClient.getUserProfile(jwt);
        Task task = taskService.assignedToUser(id, userId);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task request,
                                           @RequestHeader("Authorization") String jwt) throws Exception {

        UserDTO user = userServiceClient.getUserProfile(jwt);
        Task updateTask = taskService.updateTask(id, request, user.getId());
        return new ResponseEntity<>(updateTask, HttpStatus.OK);
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<Task> completeTask(@PathVariable Long id) throws Exception {
        Task completeTask = taskService.completeTask(id);
        return new ResponseEntity<>(completeTask, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) throws Exception {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

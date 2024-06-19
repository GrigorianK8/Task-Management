package com.grigoriank.taskSubmissionService.repository;

import com.grigoriank.taskSubmissionService.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    List<Submission> findByTaskId(Long taskId);
}

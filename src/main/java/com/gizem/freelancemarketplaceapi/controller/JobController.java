package com.gizem.freelancemarketplaceapi.controller;

import com.gizem.freelancemarketplaceapi.exception.ResourceNotFoundException;
import com.gizem.freelancemarketplaceapi.dto.JobDTO;
import com.gizem.freelancemarketplaceapi.entity.Job;
import com.gizem.freelancemarketplaceapi.service.JobService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v{version}/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping
    public ResponseEntity<Job> createJob(@PathVariable String version, @RequestBody @Valid JobDTO dto) {
        return ResponseEntity.ok(jobService.createJob(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJob(@PathVariable String version, @PathVariable Long id) {
        Job job = jobService.getJob(id);
        if (job == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(job);
    }

    @GetMapping("/freelancer/{freelancerId}")
    public ResponseEntity<List<Job>> getJobsByFreelancer(@PathVariable String version, @PathVariable Long freelancerId) {
        return ResponseEntity.ok(jobService.getJobsByFreelancer(freelancerId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable String version, @PathVariable Long id, @RequestBody JobDTO dto) {
        Job updated = jobService.updateJob(id, dto);
        if (updated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(updated);
    }
}
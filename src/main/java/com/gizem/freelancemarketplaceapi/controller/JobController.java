package com.gizem.freelancemarketplaceapi.controller;

import com.gizem.freelancemarketplaceapi.exception.ResourceNotFoundException;
import com.gizem.freelancemarketplaceapi.dto.JobDTO;
import com.gizem.freelancemarketplaceapi.entity.Job;
import com.gizem.freelancemarketplaceapi.service.JobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/{version}/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping
    public ResponseEntity<Job> createJob(@PathVariable String version, @RequestBody JobDTO dto) {
        return ResponseEntity.ok(jobService.createJob(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJob(@PathVariable String version, @PathVariable Long id) {
        Job job = jobService.getJob(id);
        if (job == null) {
            throw new ResourceNotFoundException("Job not found with id: " + id);
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
            throw new ResourceNotFoundException("Job not found with id: " + id);
        }
        return ResponseEntity.ok(updated);
    }
}
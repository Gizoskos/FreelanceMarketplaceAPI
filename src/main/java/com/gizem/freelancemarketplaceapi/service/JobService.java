package com.gizem.freelancemarketplaceapi.service;

import com.gizem.freelancemarketplaceapi.dto.JobDTO;
import com.gizem.freelancemarketplaceapi.entity.Job;

import java.util.List;

public interface JobService {
    Job createJob(JobDTO dto);
    Job getJob(Long id);
    List<Job> getJobsByFreelancer(Long freelancerId);
    Job updateJob(Long id, JobDTO dto);
}
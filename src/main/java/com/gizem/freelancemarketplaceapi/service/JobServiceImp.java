package com.gizem.freelancemarketplaceapi.service;

import com.gizem.freelancemarketplaceapi.dto.JobDTO;
import com.gizem.freelancemarketplaceapi.entity.Job;
import com.gizem.freelancemarketplaceapi.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class JobServiceImp implements JobService {

    private final JobRepository jobRepository;

    public JobServiceImp(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public Job createJob(JobDTO dto) {
        Job job = new Job();
        job.setFreelancerId(dto.getFreelancerId());
        job.setDescription(dto.getDescription());
        job.setStatus(dto.getStatus());
        job.setCreatedDate(dto.getCreatedDate() != null ? dto.getCreatedDate() : LocalDate.now());
        return jobRepository.save(job);
    }

    @Override
    public Job getJob(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public List<Job> getJobsByFreelancer(Long freelancerId) {
        return jobRepository.findByFreelancerId(freelancerId);
    }

    @Override
    public Job updateJob(Long id, JobDTO dto) {
        Job job = getJob(id);
        if (job == null) {
            return null;
        }
        if (dto.getDescription() != null) {
            job.setDescription(dto.getDescription());
        }
        if (dto.getStatus() != null) {
            job.setStatus(dto.getStatus());
        }
        return jobRepository.save(job);
    }
}
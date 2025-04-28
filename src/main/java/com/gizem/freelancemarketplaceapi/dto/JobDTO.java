package com.gizem.freelancemarketplaceapi.dto;

import com.gizem.freelancemarketplaceapi.entity.JobStatus;

import java.time.LocalDate;

public class JobDTO {
    private Long freelancerId;
    private String description;
    private JobStatus status; // In progress, finished
    private LocalDate createdDate;

    public JobDTO() {
    }

    public JobDTO(Long freelancerId, String description, JobStatus status, LocalDate createdDate) {
        this.freelancerId = freelancerId;
        this.description = description;
        this.status = status;
        this.createdDate = createdDate;
    }

    public Long getFreelancerId() {
        return freelancerId;
    }

    public void setFreelancerId(Long freelancerId) {
        this.freelancerId = freelancerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
}
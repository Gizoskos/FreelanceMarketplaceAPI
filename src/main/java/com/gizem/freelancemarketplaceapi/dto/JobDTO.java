package com.gizem.freelancemarketplaceapi.dto;

import com.gizem.freelancemarketplaceapi.entity.JobStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class JobDTO {
    @NotNull(message = "FreelancerId cannot be null")
    private Long freelancerId;
    @NotBlank(message = "Description cannot be blank")
    private String description;
    @NotNull(message = "Status cannot be null")
    private JobStatus status; // In progress, finished
    @Schema(type = "string", pattern = "yyyy-MM-dd", example = "2025-04-28")

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
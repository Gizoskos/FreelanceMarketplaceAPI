package com.gizem.freelancemarketplaceapi.entity;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long freelancerId;
    private String description;
    @Enumerated(EnumType.STRING)
    private JobStatus status;

    private LocalDate createdDate;
    @PrePersist
    protected void onCreate() {
        this.createdDate = LocalDate.now();
    }
    public Job() {
    }

    public Job(Long id, Long freelancerId, String description, JobStatus status, LocalDate createdDate) {
        this.id = id;
        this.freelancerId = freelancerId;
        this.description = description;
        this.status = status;
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
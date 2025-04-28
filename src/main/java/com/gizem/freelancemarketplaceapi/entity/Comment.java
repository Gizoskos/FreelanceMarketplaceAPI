package com.gizem.freelancemarketplaceapi.entity;


import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long jobId;
    private String commenterName;

    private LocalDate createdDate;

    private String comment;

    public Comment() {}

    public Comment(Long id, Long jobId, String commenterName, LocalDate createdDate, String comment) {
        this.id = id;
        this.jobId = jobId;
        this.commenterName = commenterName;
        this.createdDate = createdDate;
        this.comment = comment;
    }
    @PrePersist
    protected void onCreate() {
        this.createdDate = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getCommenterName() {
        return commenterName;
    }

    public void setCommenterName(String commenterName) {
        this.commenterName = commenterName;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
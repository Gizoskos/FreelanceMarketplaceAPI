package com.gizem.freelancemarketplaceapi.dto;

import java.time.LocalDate;


public class CommentDTO {
    private Long jobId;
    private String commenterName;
    private LocalDate createdDate;
    private String comment;

    public CommentDTO() {}

    public CommentDTO(Long jobId, String commenterName, LocalDate createdDate, String comment) {
        this.jobId = jobId;
        this.commenterName = commenterName;
        this.createdDate = createdDate;
        this.comment = comment;
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
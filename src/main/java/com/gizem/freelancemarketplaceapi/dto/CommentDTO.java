package com.gizem.freelancemarketplaceapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;


public class CommentDTO {
    @NotNull(message = "JobId cannot be null")
    private Long jobId;
    @NotBlank(message = "Commenter name cannot be blank")
    private String commenterName;
    @Schema(type = "string", pattern = "yyyy-MM-dd", example = "2025-04-28")
    private LocalDate createdDate;
    @NotBlank(message = "Comment cannot be blank")
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
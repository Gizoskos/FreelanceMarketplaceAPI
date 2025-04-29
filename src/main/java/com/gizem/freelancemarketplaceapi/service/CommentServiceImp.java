package com.gizem.freelancemarketplaceapi.service;

import com.gizem.freelancemarketplaceapi.dto.CommentDTO;
import com.gizem.freelancemarketplaceapi.entity.Comment;
import com.gizem.freelancemarketplaceapi.exception.ResourceNotFoundException;
import com.gizem.freelancemarketplaceapi.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CommentServiceImp implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImp(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment createComment(CommentDTO dto) {
        Comment comment = new Comment();
        comment.setJobId(dto.getJobId());
        comment.setCommenterName(dto.getCommenterName());
        comment.setComment(dto.getComment());
        comment.setCreatedDate(dto.getCreatedDate() != null ? dto.getCreatedDate() : LocalDate.now());
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getCommentsByJobId(Long jobId) {
        return commentRepository.findByJobId(jobId);
    }

    @Override
    public Comment updateComment(Long commentId, CommentDTO dto) {
        Comment existing = commentRepository.findById(commentId).orElse(null);
        if (dto.getComment() != null) {
            existing.setComment(dto.getComment());
        }
        return commentRepository.save(existing);
    }
}
package com.gizem.freelancemarketplaceapi.service;

import com.gizem.freelancemarketplaceapi.dto.CommentDTO;
import com.gizem.freelancemarketplaceapi.entity.Comment;

import java.util.List;

public interface CommentService {
    Comment createComment(CommentDTO dto);
    List<Comment> getCommentsByJobId(Long jobId);
    Comment updateComment(Long commentId, CommentDTO dto);
}
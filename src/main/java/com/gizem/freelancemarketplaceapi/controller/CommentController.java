package com.gizem.freelancemarketplaceapi.controller;

import com.gizem.freelancemarketplaceapi.exception.ResourceNotFoundException;
import com.gizem.freelancemarketplaceapi.dto.CommentDTO;
import com.gizem.freelancemarketplaceapi.entity.Comment;
import com.gizem.freelancemarketplaceapi.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/{version}/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<Comment> createComment(@PathVariable String version, @RequestBody CommentDTO dto) {
        return ResponseEntity.ok(commentService.createComment(dto));
    }

    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<Comment>> getCommentsByJob(@PathVariable String version, @PathVariable Long jobId) {
        return ResponseEntity.ok(commentService.getCommentsByJobId(jobId));
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable String version, @PathVariable Long commentId, @RequestBody CommentDTO dto) {
        Comment updated = commentService.updateComment(commentId, dto);
        if (updated == null) {
            throw new ResourceNotFoundException("Comment not found with id: " + commentId);
        }
        return ResponseEntity.ok(updated);
    }
}
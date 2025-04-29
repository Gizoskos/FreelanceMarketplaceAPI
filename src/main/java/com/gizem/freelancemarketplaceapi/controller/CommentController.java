package com.gizem.freelancemarketplaceapi.controller;

import com.gizem.freelancemarketplaceapi.exception.ResourceNotFoundException;
import com.gizem.freelancemarketplaceapi.dto.CommentDTO;
import com.gizem.freelancemarketplaceapi.entity.Comment;
import com.gizem.freelancemarketplaceapi.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v{version}/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<Comment> createComment(@PathVariable String version, @RequestBody @Valid CommentDTO dto) {
        return ResponseEntity.ok(commentService.createComment(dto));
    }

    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<Comment>> getCommentsByJob(@PathVariable String version, @PathVariable Long jobId) {
        return ResponseEntity.ok(commentService.getCommentsByJobId(jobId));
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable String version, @PathVariable Long commentId, @RequestBody @Valid CommentDTO dto) {
        Comment updated = commentService.updateComment(commentId, dto);
        if (updated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(updated);
    }
}
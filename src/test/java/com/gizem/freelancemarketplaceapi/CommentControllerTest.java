package com.gizem.freelancemarketplaceapi;

import com.gizem.freelancemarketplaceapi.controller.CommentController;
import com.gizem.freelancemarketplaceapi.entity.Comment;
import com.gizem.freelancemarketplaceapi.service.CommentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CommentController.class)
class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

    @Test
    void createComment_ShouldReturnOk() throws Exception {
        Mockito.when(commentService.createComment(any())).thenReturn(new Comment());

        mockMvc.perform(post("/api/v1/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                              "jobId": 1,
                              "commenterName": "Tester",
                              "comment": "Test comment"
                            }
                        """))
                .andExpect(status().isOk());
    }
    @Test
    void createComment_ShouldReturnBadRequest() throws Exception {
        mockMvc.perform(post("/api/v1/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                              "comment": "Missing jobId and commenterName"
                            }
                        """))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getCommentsByJob_ShouldReturnOk() throws Exception {
        Mockito.when(commentService.getCommentsByJobId(1L)).thenReturn(List.of(new Comment()));

        mockMvc.perform(get("/api/v1/comments/job/1"))
                .andExpect(status().isOk());
    }
    @Test
    void getCommentsByJob_ShouldReturnEmptyList() throws Exception {
        Mockito.when(commentService.getCommentsByJobId(1L)).thenReturn(List.of());

        mockMvc.perform(get("/api/v1/comments/job/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }


    @Test
    void updateComment_ShouldReturnOk() throws Exception {
        Comment updatedComment = new Comment();
        updatedComment.setId(1L);
        updatedComment.setComment("Updated comment");

        Mockito.when(commentService.updateComment(eq(1L), any())).thenReturn(updatedComment);

        mockMvc.perform(put("/api/v1/comments/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                              "jobId": 1,
                              "commenterName": "Tester",
                              "comment": "Updated comment"
                            }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.comment").value("Updated comment"));
    }

    @Test
    void updateComment_ShouldReturnNotFound() throws Exception {
        Mockito.when(commentService.updateComment(eq(100L), any())).thenReturn(null);

        mockMvc.perform(put("/api/v1/comments/100")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                              "jobId": 1,
                              "commenterName": "Tester",
                              "comment": "Updated comment"
                            }
                        """))
                .andExpect(status().isNotFound());
    }
}

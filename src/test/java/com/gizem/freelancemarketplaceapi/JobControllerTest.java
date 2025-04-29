package com.gizem.freelancemarketplaceapi;

import com.gizem.freelancemarketplaceapi.controller.JobController;
import com.gizem.freelancemarketplaceapi.entity.Job;
import com.gizem.freelancemarketplaceapi.exception.ResourceNotFoundException;
import com.gizem.freelancemarketplaceapi.service.JobService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(JobController.class)
class JobControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JobService jobService;

    @Test
    void createJob_ShouldReturnOk() throws Exception {
        Mockito.when(jobService.createJob(any())).thenReturn(new Job());

        mockMvc.perform(post("/api/v1/jobs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                              "freelancerId": 1,
                              "description": "Job Description",
                              "status": "IN_PROGRESS"
                            }
                        """))
                .andExpect(status().isOk());
    }
    @Test
    void createJob_ShouldReturnBadRequest() throws Exception {
        mockMvc.perform(post("/api/v1/jobs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                              "description": "Missing freelancerId and status"
                            }
                        """))
                .andExpect(status().isBadRequest());
    }
    @Test
    void getJobById_ShouldReturnOk() throws Exception {
        Job job = new Job();
        job.setId(1L);
        job.setDescription("Job Description");

        Mockito.when(jobService.getJob(1L)).thenReturn(job);

        mockMvc.perform(get("/api/v1/jobs/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("Job Description"));
    }

    @Test
    void getJobById_ShouldReturnNotFound() throws Exception {
        Mockito.when(jobService.getJob(999L)).thenReturn(null);

        mockMvc.perform(get("/api/v1/jobs/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getJobsByFreelancer_ShouldReturnOk() throws Exception {
        Mockito.when(jobService.getJobsByFreelancer(1L)).thenReturn(List.of(new Job()));

        mockMvc.perform(get("/api/v1/jobs/freelancer/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getJobsByFreelancer_ShouldReturnEmptyList() throws Exception {
        Mockito.when(jobService.getJobsByFreelancer(2L)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/v1/jobs/freelancer/2"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }
    @Test
    void updateJob_ShouldReturnOk() throws Exception {
        Mockito.when(jobService.updateJob(Mockito.eq(1L), any())).thenReturn(new Job());

        mockMvc.perform(put("/api/v1/jobs/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                          "freelancerId": 1,
                          "description": "Updated Job Description",
                          "status": "FINISHED"
                        }
                    """))
                .andExpect(status().isOk());
    }
    @Test
    void updateJob_NotFound() throws Exception {
        Mockito.when(jobService.updateJob(Mockito.eq(999L), any())).thenReturn(null);

        mockMvc.perform(put("/api/v1/jobs/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                          "freelancerId": 1,
                          "description": "Updated Job Description",
                          "status": "FINISHED"
                        }
                    """)).andExpect(result ->
                assertTrue(result.getResolvedException() instanceof ResourceNotFoundException)
        );
    }
}

package com.gizem.freelancemarketplaceapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gizem.freelancemarketplaceapi.controller.FreelancerController;
import com.gizem.freelancemarketplaceapi.entity.Freelancer;
import com.gizem.freelancemarketplaceapi.service.FreelancerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FreelancerController.class)
class FreelancerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FreelancerService freelancerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createFreelancer_ShouldReturnOk() throws Exception {
        Mockito.when(freelancerService.createFreelancer(any())).thenReturn(new Freelancer());

        mockMvc.perform(post("/api/v1/freelancers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                              "name": "Tester",
                              "email": "test@example.com",
                              "phone": "123456789",
                              "city": "Test City",
                              "freelancerType": "SOFTWARE_DEVELOPER"
                            }
                        """))
                .andExpect(status().isOk());
    }

    @Test
    void createFreelancer_ShouldReturnBadRequest() throws Exception {
        mockMvc.perform(post("/api/v1/freelancers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                              "email": "test@example.com"
                            }
                        """))
                .andExpect(status().isBadRequest());
    }
    @Test
    void getAll_ReturnsFreelancersList() throws Exception {
        when(freelancerService.getAllFreelancers())
                .thenReturn(Collections.singletonList(new Freelancer()));

        mockMvc.perform(get("/api/v1/freelancers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
    @Test
    void getAll_ShouldReturnEmptyList() throws Exception {
        when(freelancerService.getAllFreelancers())
                .thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/v1/freelancers"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void getFreelancerById_NotFound() throws Exception {
        Mockito.when(freelancerService.getFreelancer(999L)).thenReturn(null);

        mockMvc.perform(get("/api/v1/freelancers/999"))
                .andExpect(status().isNotFound());
    }
    @Test
    void searchFreelancers_ShouldReturnOk() throws Exception {
        Mockito.when(freelancerService.searchBy(any(), any(), any(), any(), any())).thenReturn(List.of(new Freelancer()));

        mockMvc.perform(get("/api/v1/freelancers/search?city=TestCity"))
                .andExpect(status().isOk());
    }
    @Test
    void searchFreelancers_ShouldReturnEmptyList() throws Exception {
        Mockito.when(freelancerService.searchBy(any(), any(), any(), any(), any()))
                .thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/v1/freelancers/search?city=NoSuchCity"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }
}


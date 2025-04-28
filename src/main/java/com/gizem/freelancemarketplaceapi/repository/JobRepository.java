package com.gizem.freelancemarketplaceapi.repository;

import com.gizem.freelancemarketplaceapi.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByFreelancerId(Long freelancerId);
}
package com.gizem.freelancemarketplaceapi.dto;

import com.gizem.freelancemarketplaceapi.entity.FreelancerType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class FreelancerDTO {
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotBlank(message = "Email cannot be blank")
    private String email;
    private String phone;
    private String city;
    @NotNull(message = "Freelancer type cannot be null")
    private FreelancerType freelancerType;
    private String portfolioUrl;
    private String designTools;
    private String softwareLanguages;
    private String specialties;

    public FreelancerDTO() {}

    public FreelancerDTO(String name, String email, String phone, String city, FreelancerType freelancerType,
                         String portfolioUrl, String designTools,
                         String softwareLanguages, String specialties) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.city = city;
        this.freelancerType = freelancerType;
        this.portfolioUrl = portfolioUrl;
        this.designTools = designTools;
        this.softwareLanguages = softwareLanguages;
        this.specialties = specialties;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public FreelancerType getFreelancerType() {
        return freelancerType;
    }

    public void setFreelancerType(FreelancerType freelancerType) {
        this.freelancerType = freelancerType;
    }

    public String getPortfolioUrl() {
        return portfolioUrl;
    }

    public void setPortfolioUrl(String portfolioUrl) {
        this.portfolioUrl = portfolioUrl;
    }

    public String getDesignTools() {
        return designTools;
    }

    public void setDesignTools(String designTools) {
        this.designTools = designTools;
    }

    public String getSoftwareLanguages() {
        return softwareLanguages;
    }

    public void setSoftwareLanguages(String softwareLanguages) {
        this.softwareLanguages = softwareLanguages;
    }

    public String getSpecialties() {
        return specialties;
    }

    public void setSpecialties(String specialties) {
        this.specialties = specialties;
    }
}

package com.placement.management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "admins")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;

    @NotBlank
    @Column(nullable = false)
    private String username;

    private Long contactNo;

    @NotBlank
    @Column(nullable = false)
    private String role;

    public Admin() {
    }

    public Admin(Long adminId, String username, Long contactNo, String role) {
        this.adminId = adminId;
        this.username = username;
        this.contactNo = contactNo;
        this.role = role;
    }

    public Admin(String username, Long contactNo, String role) {
        this.username = username;
        this.contactNo = contactNo;
        this.role = role;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getContactNo() {
        return contactNo;
    }

    public void setContactNo(Long contactNo) {
        this.contactNo = contactNo;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Admin{adminId=" + adminId + ", username='" + username + "', role='" + role + "'}";
    }
}

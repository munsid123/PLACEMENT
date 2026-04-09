package com.placement.management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank
    @Column(nullable = false)
    private String username;

    private Long contactNo;

    @NotBlank
    @Column(nullable = false)
    private String stream;

    public User() {
    }

    public User(Long userId, String username, Long contactNo, String stream) {
        this.userId = userId;
        this.username = username;
        this.contactNo = contactNo;
        this.stream = stream;
    }

    public User(String username, Long contactNo, String stream) {
        this.username = username;
        this.contactNo = contactNo;
        this.stream = stream;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    @Override
    public String toString() {
        return "User{userId=" + userId + ", username='" + username + "', contactNo=" + contactNo
                + ", stream='" + stream + "'}";
    }
}

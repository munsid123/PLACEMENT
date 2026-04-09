package com.placement.management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "placements")
public class Placement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long placementId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin;

    @NotBlank
    @Column(nullable = false)
    private String companyName;

    @NotBlank
    @Column(nullable = false)
    private String status;

    public Placement() {
    }

    public Placement(Long placementId, User user, Admin admin, String companyName, String status) {
        this.placementId = placementId;
        this.user = user;
        this.admin = admin;
        this.companyName = companyName;
        this.status = status;
    }

    public Placement(User user, Admin admin, String companyName, String status) {
        this.user = user;
        this.admin = admin;
        this.companyName = companyName;
        this.status = status;
    }

    public Long getPlacementId() {
        return placementId;
    }

    public void setPlacementId(Long placementId) {
        this.placementId = placementId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Placement{placementId=" + placementId + ", user=" + user.getUsername()
                + ", admin=" + admin.getUsername() + ", companyName='" + companyName
                + "', status='" + status + "'}";
    }
}

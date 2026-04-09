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
@Table(name = "certificates")
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long certificateId;

    @NotBlank
    @Column(nullable = false)
    private String title;

    @NotBlank
    @Column(nullable = false)
    private String issuer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Certificate() {
    }

    public Certificate(Long certificateId, String title, String issuer, User user) {
        this.certificateId = certificateId;
        this.title = title;
        this.issuer = issuer;
        this.user = user;
    }

    public Certificate(String title, String issuer, User user) {
        this.title = title;
        this.issuer = issuer;
        this.user = user;
    }

    public Long getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(Long certificateId) {
        this.certificateId = certificateId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Certificate{certificateId=" + certificateId + ", title='" + title
                + "', issuer='" + issuer + "', user=" + user.getUsername() + "}";
    }
}

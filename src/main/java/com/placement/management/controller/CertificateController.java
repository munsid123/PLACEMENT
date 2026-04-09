package com.placement.management.controller;

import com.placement.management.entity.Certificate;
import com.placement.management.service.CertificateService;
import java.util.List;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/certificates")
public class CertificateController {

    private final CertificateService certificateService;

    @Autowired
    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @PostMapping
    public ResponseEntity<Certificate> createCertificate(@Valid @RequestBody Certificate certificate) {
        return ResponseEntity.status(HttpStatus.CREATED).body(certificateService.createCertificate(certificate));
    }

    @GetMapping
    public List<Certificate> getAllCertificates() {
        return certificateService.getAllCertificates();
    }

    @GetMapping("/{certificateId}")
    public Certificate getCertificateById(@PathVariable Long certificateId) {
        return certificateService.getCertificateById(certificateId);
    }

    @PutMapping("/{certificateId}")
    public Certificate updateCertificate(@PathVariable Long certificateId, @Valid @RequestBody Certificate certificate) {
        return certificateService.updateCertificate(certificateId, certificate);
    }

    @DeleteMapping("/{certificateId}")
    public ResponseEntity<Void> deleteCertificate(@PathVariable Long certificateId) {
        certificateService.deleteCertificate(certificateId);
        return ResponseEntity.noContent().build();
    }
}
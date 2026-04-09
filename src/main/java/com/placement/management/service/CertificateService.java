package com.placement.management.service;

import com.placement.management.entity.Certificate;
import com.placement.management.entity.User;
import com.placement.management.repository.CertificateRepository;
import com.placement.management.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CertificateService {

    private final CertificateRepository certificateRepository;
    private final UserRepository userRepository;

    @Autowired
    public CertificateService(CertificateRepository certificateRepository, UserRepository userRepository) {
        this.certificateRepository = certificateRepository;
        this.userRepository = userRepository;
    }

    public Certificate createCertificate(Certificate certificate) {
        certificate.setUser(resolveUser(certificate.getUser()));
        return certificateRepository.save(certificate);
    }

    public List<Certificate> getAllCertificates() {
        return certificateRepository.findAll();
    }

    public Certificate getCertificateById(Long certificateId) {
        return certificateRepository.findById(certificateId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Certificate not found"));
    }

    public Certificate updateCertificate(Long certificateId, Certificate updatedCertificate) {
        Certificate existingCertificate = getCertificateById(certificateId);
        existingCertificate.setTitle(updatedCertificate.getTitle());
        existingCertificate.setIssuer(updatedCertificate.getIssuer());
        existingCertificate.setUser(resolveUser(updatedCertificate.getUser()));
        return certificateRepository.save(existingCertificate);
    }

    public void deleteCertificate(Long certificateId) {
        if (!certificateRepository.existsById(certificateId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Certificate not found");
        }
        certificateRepository.deleteById(certificateId);
    }

    private User resolveUser(User user) {
        if (user == null || user.getUserId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User id is required for certificate");
        }
        return userRepository.findById(user.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }
}
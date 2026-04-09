package com.placement.management.service;

import com.placement.management.entity.Admin;
import com.placement.management.repository.AdminRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Admin getAdminById(Long adminId) {
        return adminRepository.findById(adminId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin not found"));
    }

    public Admin updateAdmin(Long adminId, Admin updatedAdmin) {
        Admin existingAdmin = getAdminById(adminId);
        existingAdmin.setUsername(updatedAdmin.getUsername());
        existingAdmin.setContactNo(updatedAdmin.getContactNo());
        existingAdmin.setRole(updatedAdmin.getRole());
        return adminRepository.save(existingAdmin);
    }

    public void deleteAdmin(Long adminId) {
        if (!adminRepository.existsById(adminId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin not found");
        }
        adminRepository.deleteById(adminId);
    }
}
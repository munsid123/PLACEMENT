package com.placement.management.controller;

import com.placement.management.entity.Admin;
import com.placement.management.service.AdminService;
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
@RequestMapping("/api/admins")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping
    public ResponseEntity<Admin> createAdmin(@Valid @RequestBody Admin admin) {
        return ResponseEntity.status(HttpStatus.CREATED).body(adminService.createAdmin(admin));
    }

    @GetMapping
    public List<Admin> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    @GetMapping("/{adminId}")
    public Admin getAdminById(@PathVariable Long adminId) {
        return adminService.getAdminById(adminId);
    }

    @PutMapping("/{adminId}")
    public Admin updateAdmin(@PathVariable Long adminId, @Valid @RequestBody Admin admin) {
        return adminService.updateAdmin(adminId, admin);
    }

    @DeleteMapping("/{adminId}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long adminId) {
        adminService.deleteAdmin(adminId);
        return ResponseEntity.noContent().build();
    }
}
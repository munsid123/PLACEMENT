package com.placement.management.service;

import com.placement.management.entity.Admin;
import com.placement.management.entity.Placement;
import com.placement.management.entity.User;
import com.placement.management.repository.AdminRepository;
import com.placement.management.repository.PlacementRepository;
import com.placement.management.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PlacementService {

    private final PlacementRepository placementRepository;
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;

    @Autowired
    public PlacementService(PlacementRepository placementRepository,
                            UserRepository userRepository,
                            AdminRepository adminRepository) {
        this.placementRepository = placementRepository;
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
    }

    public Placement createPlacement(Placement placement) {
        placement.setUser(resolveUser(placement.getUser()));
        placement.setAdmin(resolveAdmin(placement.getAdmin()));
        return placementRepository.save(placement);
    }

    public List<Placement> getAllPlacements() {
        return placementRepository.findAll();
    }

    public Placement getPlacementById(Long placementId) {
        return placementRepository.findById(placementId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Placement not found"));
    }

    public Placement updatePlacement(Long placementId, Placement updatedPlacement) {
        Placement existingPlacement = getPlacementById(placementId);
        existingPlacement.setCompanyName(updatedPlacement.getCompanyName());
        existingPlacement.setStatus(updatedPlacement.getStatus());
        existingPlacement.setUser(resolveUser(updatedPlacement.getUser()));
        existingPlacement.setAdmin(resolveAdmin(updatedPlacement.getAdmin()));
        return placementRepository.save(existingPlacement);
    }

    public void deletePlacement(Long placementId) {
        if (!placementRepository.existsById(placementId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Placement not found");
        }
        placementRepository.deleteById(placementId);
    }

    private User resolveUser(User user) {
        if (user == null || user.getUserId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User id is required for placement");
        }
        return userRepository.findById(user.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    private Admin resolveAdmin(Admin admin) {
        if (admin == null || admin.getAdminId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Admin id is required for placement");
        }
        return adminRepository.findById(admin.getAdminId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin not found"));
    }
}
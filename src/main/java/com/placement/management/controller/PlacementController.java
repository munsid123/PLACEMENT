package com.placement.management.controller;

import com.placement.management.entity.Placement;
import com.placement.management.service.PlacementService;
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
@RequestMapping("/api/placements")
public class PlacementController {

    private final PlacementService placementService;

    @Autowired
    public PlacementController(PlacementService placementService) {
        this.placementService = placementService;
    }

    @PostMapping
    public ResponseEntity<Placement> createPlacement(@Valid @RequestBody Placement placement) {
        return ResponseEntity.status(HttpStatus.CREATED).body(placementService.createPlacement(placement));
    }

    @GetMapping
    public List<Placement> getAllPlacements() {
        return placementService.getAllPlacements();
    }

    @GetMapping("/{placementId}")
    public Placement getPlacementById(@PathVariable Long placementId) {
        return placementService.getPlacementById(placementId);
    }

    @PutMapping("/{placementId}")
    public Placement updatePlacement(@PathVariable Long placementId, @Valid @RequestBody Placement placement) {
        return placementService.updatePlacement(placementId, placement);
    }

    @DeleteMapping("/{placementId}")
    public ResponseEntity<Void> deletePlacement(@PathVariable Long placementId) {
        placementService.deletePlacement(placementId);
        return ResponseEntity.noContent().build();
    }
}
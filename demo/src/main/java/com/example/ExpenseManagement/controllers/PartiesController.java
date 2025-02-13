package com.example.ExpenseManagement.controllers;

import com.example.ExpenseManagement.entities.Parties;
import com.example.ExpenseManagement.services.PartiesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/parties")
public class PartiesController {

    private static final Logger logger = LoggerFactory.getLogger(PartiesController.class);

    @Autowired
    private PartiesService partiesService;

    @GetMapping("/all")
    public List<Parties> getAllParties()
    {
        logger.info("GET request received for all parties.");
        return partiesService.getAllParties();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Parties> getPartyById(@PathVariable String id) {
        logger.info("GET request received for party with ID: {}", id);
        Optional<Parties> party = partiesService.getPartyById(id);
        return party.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<Parties> createParty(@RequestBody Parties party)
    {
        logger.info("POST request received for creating a new party: {}", party.getPartyName());
        return ResponseEntity.ok(partiesService.createParty(party));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Parties> updateParty(@PathVariable String id, @RequestBody Parties updatedParty) {
        return ResponseEntity.ok(partiesService.updateParty(id, updatedParty));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParty(@PathVariable String id) {
        partiesService.deleteParty(id);
        return ResponseEntity.noContent().build();
    }
}

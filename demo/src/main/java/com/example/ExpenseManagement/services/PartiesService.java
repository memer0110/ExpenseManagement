package com.example.ExpenseManagement.services;

import com.example.ExpenseManagement.entities.Parties;
import com.example.ExpenseManagement.repositories.PartiesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PartiesService {

    private static final Logger logger = LoggerFactory.getLogger(PartiesService.class);

    @Autowired
    private PartiesRepository partiesRepository;

    public List<Parties> getAllParties() {
        logger.info("Fetching all parties from the database.");
        return partiesRepository.findAll();
    }

    public Optional<Parties> getPartyById(String id) {
        logger.debug("Fetching party with ID: {}", id);
        return partiesRepository.findById(id);
    }

    public Parties createParty(Parties party) {
        logger.info("Saving new party: {}", party.getPartyName());
        return partiesRepository.save(party);
    }

    public Parties updateParty(String id, Parties updatedParty) {
        return partiesRepository.findById(id).map(party -> {
            party.setPartyName(updatedParty.getPartyName());
            party.setContactNumber(updatedParty.getContactNumber());
            party.setAddress(updatedParty.getAddress());
            party.setCategory(updatedParty.getCategory());
            party.setPan(updatedParty.getPan());
            party.setGst(updatedParty.getGst());
            party.setNotes(updatedParty.getNotes());
            return partiesRepository.save(party);
        }).orElseThrow(() -> new RuntimeException("Party not found with id: " + id));
    }

    public void deleteParty(String id) {
        partiesRepository.deleteById(id);
    }
}

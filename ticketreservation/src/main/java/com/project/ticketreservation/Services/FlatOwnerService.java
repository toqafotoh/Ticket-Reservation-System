package com.project.ticketreservation.Services;

import com.project.ticketreservation.Models.FlatOwner;
import com.project.ticketreservation.Repositories.FlatOwnerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlatOwnerService {

    @Autowired
    private FlatOwnerRepository flatOwnerRepository;

    public List<FlatOwner> getAllOwners() {
        return flatOwnerRepository.findAll();
    }

    public void updateRate(String ownerId, Integer rate) {
        FlatOwner existingOwner = flatOwnerRepository.findById(ownerId)
                .orElseThrow(() -> new EntityNotFoundException("Flat owner not found with ID: " + ownerId));

        existingOwner.setFlatOwnerRate(rate);
        flatOwnerRepository.save(existingOwner);
    }

    public FlatOwner getOwnerById(String ownerId) {
        return flatOwnerRepository.findById(ownerId).orElseThrow(() -> new EntityNotFoundException("FlatOwner not found with ID: " + ownerId));
    }

    public boolean deleteOwner(String ownerId) {
        if (flatOwnerRepository.existsById(ownerId)) {
            flatOwnerRepository.deleteById(ownerId);
            return true;
        } else {
            return false;
        }
    }
}
package com.project.ticketreservation.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.project.ticketreservation.Models.Flat;
import com.project.ticketreservation.Models.FlatOwner;
import com.project.ticketreservation.Models.Passenger;
import com.project.ticketreservation.Repositories.FlatOwnerRepository;
import com.project.ticketreservation.Repositories.FlatRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class FlatOwnerService {

    @Autowired
    private FlatOwnerRepository flatOwnerRepository;
    @Autowired
    private FlatRepository flatRepository;

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
        return flatOwnerRepository.findById(ownerId)
                .orElseThrow(() -> new EntityNotFoundException("FlatOwner not found with ID: " + ownerId));
    }

    public boolean deleteOwner(String ownerId) {
        if (flatOwnerRepository.existsById(ownerId)) {
            flatOwnerRepository.deleteById(ownerId);
            return true;
        } else {
            return false;
        }
    }

    public FlatOwner getFlatOwnerByFlatId(Integer flatId) {
        Flat flat = flatRepository.findById(flatId)
                .orElseThrow(() -> new EntityNotFoundException("Flat not found with ID: " + flatId));

        String ownerId = flat.getFlatOwnerId();
        return flatOwnerRepository.findById(ownerId)
                .orElseThrow(() -> new EntityNotFoundException("Owner not found with ID: " + ownerId));
    }

    public UserDetails save(FlatOwner flatOwner) {
        System.out.println("Saving FlatOwner");
        return flatOwnerRepository.save(flatOwner);
    }
}
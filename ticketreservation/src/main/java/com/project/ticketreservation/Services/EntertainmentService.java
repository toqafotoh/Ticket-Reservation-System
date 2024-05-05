package com.project.ticketreservation.Services;

import com.project.ticketreservation.Models.Entertainment;
import com.project.ticketreservation.Repositories.EntertainmentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class EntertainmentService {
    @Autowired
    private EntertainmentRepository entertainmentRepository;
    // this function must have ASOP constrain that nobody can call it except the admin
    public boolean AddEntertainment(Entertainment entertainment){
        entertainmentRepository.save(entertainment);
        return true;
    }
    public List<Entertainment> getEntertainments(){
        return entertainmentRepository.findAll();
    }
    public List<Entertainment> getEntertainmentByDestination(String destination){
        List<Entertainment> entertainments = entertainmentRepository.findByDestination(destination);
        return entertainments.isEmpty() ? Collections.emptyList() : entertainments;
    }

    public boolean deleteEntertainment(Integer entertainmentId){
        if (entertainmentRepository.existsById(entertainmentId)) {
            entertainmentRepository.deleteById(entertainmentId);
            return true;
        } else {
            return false;
        }
    }
    public Entertainment updateEntertainment(Integer entertainmentId,Entertainment editedEntertainment) {
        Entertainment existingEntertainment = entertainmentRepository.findById(entertainmentId)
                .orElseThrow(() -> new EntityNotFoundException("Entertainment not found with ID: " + editedEntertainment.getEntertainmentTourId()));
        if (!entertainmentId.equals(editedEntertainment.getEntertainmentTourId())) {
            throw new IllegalArgumentException("Cannot change the entertainment ID");
        }
        existingEntertainment.setPrice(editedEntertainment.getPrice());
        existingEntertainment.setDescription(editedEntertainment.getDescription());
        existingEntertainment.setDestination(editedEntertainment.getDestination());
        return entertainmentRepository.save(existingEntertainment);
    }
    public Entertainment getEntertainmentById(Integer entertainmentId) {
        return entertainmentRepository.findById(entertainmentId)
                .orElseThrow(() -> new EntityNotFoundException("Entertainment not found with ID: " + entertainmentId));
    }

}

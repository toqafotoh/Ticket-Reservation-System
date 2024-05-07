package com.project.ticketreservation.services;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.project.ticketreservation.dto.FlatDtoRespone;
import com.project.ticketreservation.dto.FlatMapper;
import com.project.ticketreservation.dto.FlatsDto;
import com.project.ticketreservation.dto.FlatsProfileDto;
import com.project.ticketreservation.models.Account;
import com.project.ticketreservation.models.Flat;
import com.project.ticketreservation.repositories.FlatsRepository;

@Service
public class FlatService {
    @Autowired
    private FlatsRepository flatRepository;

    @Autowired
    private FlatMapper flatMapper;

    public List<FlatDtoRespone> getAllFlats() {
        List<Flat> flats = flatRepository.findAll();
        return flats.stream()
                .map(flatMapper::toFlatDTORespone)
                .collect(Collectors.toList());
    }

    public List<FlatsDto> getOwnerFlats() {
        Account current = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Flat> flats = flatRepository.findByFlatOwnerId(current.getNationalId());

        List<FlatsDto> result = flats.stream()
                .map(flat -> new FlatsDto(flat))
                .collect(Collectors.toList());
        return result;
    }

    public FlatDtoRespone getFlatById(Integer id) {
        Flat flat = flatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flat not found"));
        FlatDtoRespone response = flatMapper.toFlatDTORespone(flat);
        return response;

    }

    public Flat addFlat(FlatsProfileDto flatDto) throws IOException {
        Account current = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Flat flat = new Flat(flatDto);
        flat.setFlatOwnerId(current.getNationalId());
        return flatRepository.save(flat);
    }

    public FlatDtoRespone updateFlat(Integer flatId, FlatsDto flatsDto) {
        Flat flat = flatRepository.findById(flatId)
                .orElseThrow(() -> new RuntimeException("Flat not found"));
        Flat updatedFlat = flatMapper.ChangeFlat(flat, flatsDto);
        Flat savedFlat = flatRepository.save(updatedFlat);
        return flatMapper.toFlatDTORespone(savedFlat);

    }

    public boolean deleteFlat(Integer id) {
        Flat flat = flatRepository.findById(id).orElse(null);
        if (flat != null) {
            flatRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

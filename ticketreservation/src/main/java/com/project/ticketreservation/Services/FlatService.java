package com.project.ticketreservation.Services;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ticketreservation.Models.Flat;
import com.project.ticketreservation.Repositories.FlatsRepository;
import com.project.ticketreservation.dto.FlatDtoRespone;
import com.project.ticketreservation.dto.FlatMapper;
import com.project.ticketreservation.dto.FlatsDto;

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

    public FlatDtoRespone getFlatById(Integer id) {
        Flat flat = flatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flat not found"));
        FlatDtoRespone response = flatMapper.toFlatDTORespone(flat);
        return response;

    }

    public Flat addFlat(FlatsDto flatDto) throws IOException {
        Flat flat = flatMapper.ToEntity(flatDto);
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

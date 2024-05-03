package com.project.ticketreservation.Services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ticketreservation.Models.Flat;
import com.project.ticketreservation.Repositories.FlatsRepository;
import com.project.ticketreservation.dto.FlatsDto;

@Service
public class FlatService {
    @Autowired
    private FlatsRepository flatRepository;

    // @Autowired 
    // private FlatMapper mapper;

    public List<Flat> getAllFlats() {
        return flatRepository.findAll();
    }

    public Flat getFlatById(Integer id) {
        return flatRepository.findById(id).orElse(null);
    }


    public Flat addFlat(FlatsDto flatDto) throws IOException {
        Flat flat = new Flat();
        flat.setAddress(flatDto.getAddress());
        flat.setFlatDescription(flatDto.getDescription());
        flat.setCountryName(flatDto.getCountry_name());
        flat.setCapacity(flatDto.getCapacity());
        flat.setPrice(flatDto.getPrice());
        flat.setFlatOwner(flatDto.getFlatOwner());
        return flatRepository.save(flat);
    }

    public Flat updateFlat(Integer id, FlatsDto flatDto) throws IOException {
        Flat flat = flatRepository.findById(id).orElse(null);
        
        if (flat != null) {
            flat.setAddress(flatDto.getAddress());
            flat.setFlatDescription(flatDto.getDescription());
            flat.setCountryName(flatDto.getCountry_name());
            flat.setCapacity(flatDto.getCapacity());
            flat.setPrice(flatDto.getPrice());
            flat.setFlatOwner(flatDto.getFlatOwner());
            return flatRepository.save(flat);
        }
        return null;
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

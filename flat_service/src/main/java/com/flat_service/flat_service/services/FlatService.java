package com.flat_service.flat_service.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flat_service.flat_service.dto.FlatsDto;
import com.flat_service.flat_service.models.Flat;
import com.flat_service.flat_service.repository.FlatsRepository;

@Service
public class FlatService {
    @Autowired
    private FlatsRepository flatRepository;

    public List<Flat> getAllFlats(){
        return flatRepository.findAll();
    }

    public Flat getFlatById(String id){
        return flatRepository.findById(id).orElse(null);
    }

    public Flat addFlat(FlatsDto flatDto) throws IOException{
        Flat flat = new Flat();
        flat.setAddress(flatDto.getAddress());
        flat.setCapacity(flatDto.getCapacity());
        flat.setCountry_name(flatDto.getCountry_name());
        flat.setDescription(flatDto.getDescription());
        flat.setPrice(flatDto.getPrice());
        if(flatDto.getImage() != null){
            flat.setImage(flatDto.getImage().getBytes());
        }
        return flatRepository.save(flat);

    }

    public Flat updateFlat(String id, FlatsDto flatDto) {
        Flat flat = flatRepository.findById(id).orElse(null);
        if (flat != null) {
            flat.setAddress(flatDto.getAddress());
            flat.setCapacity(flatDto.getCapacity());
            flat.setCountry_name(flatDto.getCountry_name());
            flat.setDescription(flatDto.getDescription());
            flat.setPrice(flatDto.getPrice());
            if(flatDto.getImage() != null){
                try {
                    flat.setImage(flatDto.getImage().getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return flatRepository.save(flat);
        }
        return null;
    }

    public boolean deleteFlat(String id) {
        if (flatRepository.existsById(id)) {
            flatRepository.deleteById(id);
            return true;
        }
        return false;
    }



}

package com.flat_service.flat_service.services;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flat_service.flat_service.dto.FlatMapper;
import com.flat_service.flat_service.dto.FlatsDto;
import com.flat_service.flat_service.models.Flat;
import com.flat_service.flat_service.repository.FlatsRepository;

@Service
public class FlatService {
    @Autowired
    private FlatsRepository flatRepository;

    @Autowired 
    private FlatMapper mapper;
    private final String STORAGE_PATH = System.getProperty("user.dir") + File.separator + "public" + File.separator;

    public List<Flat> getAllFlats() {
        return flatRepository.findAll();
    }

    public Flat getFlatById(Long id) {
        return flatRepository.findById(id).orElse(null);
    }

    public Flat addFlat(FlatsDto flatDto) throws IOException {
        Flat flat = mapper.toEntity(flatDto);

        String fileName = UUID.randomUUID().toString() + "_" + flatDto.getImage().getOriginalFilename();
        String filePath = STORAGE_PATH + fileName; 
        File uploadsDir = new File(STORAGE_PATH);
        if (!uploadsDir.exists()) {
            uploadsDir.mkdirs();
        }

        flat.setImagePath(filePath);
        flatDto.getImage().transferTo(new File(filePath));
        return flatRepository.save(flat); 
    }

    public Flat updateFlat(Long id, FlatsDto flatDto) throws IOException {
        Flat flat = flatRepository.findById(id).orElse(null);
        
        if (flat != null) {
            String fileName = UUID.randomUUID().toString() + "_" + flatDto.getImage().getOriginalFilename();
            String filePath = STORAGE_PATH + fileName;
            flat.setImagePath(filePath);
            flatDto.getImage().transferTo(new File(filePath));
            return flatRepository.save(flat);
        }
        return null;
    }

    public boolean deleteFlat(Long id) {
        Flat flat = flatRepository.findById(id).orElse(null);
        if (flat != null) {
            File imageFile = new File(flat.getImagePath());
            if (imageFile.exists()) {
                imageFile.delete();
            }
            flatRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

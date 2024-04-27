package com.flat_service.flat_service.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.flat_service.flat_service.dto.FlatsDto;
import com.flat_service.flat_service.models.Flat;
import com.flat_service.flat_service.services.FlatService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;





@RestController
public class FlatsController {

    @Autowired
    private FlatService flatService;

    @GetMapping("flats")
    public List<Flat> getAllFlats(){
        return flatService.getAllFlats();
    }

    @GetMapping("flats/{id}")
    public Flat getFlat(@PathVariable Long id) {
        return flatService.getFlatById(id);
    }

    @PostMapping("flats")
    public ResponseEntity<Flat> addFlat(@RequestBody FlatsDto flat) throws IOException {
        Flat savedFlat = flatService.addFlat(flat);
        return new ResponseEntity<>(savedFlat, HttpStatus.CREATED);
    }

    @PutMapping("flats/{id}")
    public ResponseEntity<Flat> updateFlat(@PathVariable Long id, @RequestBody FlatsDto flatDto) throws IOException {
        Flat updatedFlat = flatService.updateFlat(id, flatDto);
        if (updatedFlat != null) {
            return new ResponseEntity<>(updatedFlat, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("flats/{id}")
    public ResponseEntity<Void> deleteFlat(@PathVariable Long id) {
        boolean deleted = flatService.deleteFlat(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    

    
    
    
}

package com.project.ticketreservation.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ticketreservation.dto.FlatDtoRespone;
import com.project.ticketreservation.dto.FlatsDto;
import com.project.ticketreservation.dto.FlatsProfileDto;
import com.project.ticketreservation.models.Flat;
import com.project.ticketreservation.services.FlatService;

@RequestMapping("/api")
@RestController
public class FlatsController {

    @Autowired
    private FlatService flatService;

    @GetMapping("/flats")
    public List<FlatDtoRespone> getAllFlats() {
        return flatService.getAllFlats();
    }

    @GetMapping("/profile/flats")
    public List<FlatsDto> getOwnerFlats() {
        return flatService.getOwnerFlats();
    }

    @GetMapping("/flats/{id}")
    public FlatDtoRespone getFlat(@PathVariable Integer id) {
        return flatService.getFlatById(id);
    }

    @PostMapping("/flats")
    public ResponseEntity<Flat> addFlat(@RequestBody FlatsProfileDto flatDto) throws IOException {
        Flat savedFlat = flatService.addFlat(flatDto);
        return new ResponseEntity<>(savedFlat, HttpStatus.OK);
    }

    @PutMapping("/flats/{id}")
    public ResponseEntity<Flat> updateFlat(@PathVariable Integer id, @RequestBody FlatsDto flatDto) throws IOException {
        FlatDtoRespone updatedFlat = flatService.updateFlat(id, flatDto);
        if (updatedFlat != null) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/flats/{id}")
    public ResponseEntity<Void> deleteFlat(@PathVariable Integer id) {
        boolean deleted = flatService.deleteFlat(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

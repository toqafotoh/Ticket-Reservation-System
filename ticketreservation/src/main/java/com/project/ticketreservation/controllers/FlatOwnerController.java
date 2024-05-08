package com.project.ticketreservation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ticketreservation.models.FlatOwner;
import com.project.ticketreservation.services.FlatOwnerService;

@RestController
@RequestMapping
public class FlatOwnerController {
    @Autowired
    private FlatOwnerService flatOwnerService;

    @DeleteMapping("/owners/{id}")
    public boolean deleteOwner(@PathVariable String id) {
        return flatOwnerService.deleteOwner(id);
    }

    @GetMapping("/owners/{id}")
    public FlatOwner getOwnerById(@PathVariable String id) {
        return flatOwnerService.getOwnerById(id);
    }

    @GetMapping("/owners")
    public List<FlatOwner> getAllOwners() {
        return flatOwnerService.getAllOwners();
    }
}

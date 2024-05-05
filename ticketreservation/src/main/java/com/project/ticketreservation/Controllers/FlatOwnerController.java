package com.project.ticketreservation.Controllers;

import com.project.ticketreservation.Models.FlatOwner;
import com.project.ticketreservation.Repositories.FlatOwnerRepository;
import com.project.ticketreservation.Services.FlatOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

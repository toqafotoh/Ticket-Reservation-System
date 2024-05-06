package com.project.ticketreservation.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ticketreservation.Models.Entertainment;
import com.project.ticketreservation.Services.EntertainmentService;

@RestController
@RequestMapping
public class EntertainmentController {
    @Autowired
    private EntertainmentService entertainmentService;

    @GetMapping("/entertainment")
    public List<Entertainment> getAllEntertainment() {
        return entertainmentService.getEntertainments();
    }

    @PostMapping("/entertainment")
    public boolean addEntertainment(@RequestBody Entertainment entertainment) {
        return entertainmentService.AddEntertainment(entertainment);
    }

    @GetMapping("/entertainment/{id}")
    public Entertainment getEntertainmentById(@PathVariable Integer id) {
        return entertainmentService.getEntertainmentById(id);
    }

    @PutMapping("/entertainment/{id}")
    public Entertainment updateEntertainment(@PathVariable Integer id,
            @RequestBody Entertainment entertainmentDetails) {
        return entertainmentService.updateEntertainment(id, entertainmentDetails);
    }

    @DeleteMapping("/entertainment/{id}")
    public boolean deleteEntertainment(@PathVariable Integer id) {
        return entertainmentService.deleteEntertainment(id);
    }
}

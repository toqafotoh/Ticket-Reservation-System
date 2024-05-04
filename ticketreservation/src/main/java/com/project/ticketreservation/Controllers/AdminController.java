package com.project.ticketreservation.Controllers;

import com.project.ticketreservation.Models.Flight;
import com.project.ticketreservation.Models.*;
import com.project.ticketreservation.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private FlightService flightService;
    @Autowired
    private RoundTripFlightService roundTripFlightService;
    @Autowired
    private EntertainmentService entertainmentService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private PassengerService passengerService;
    @Autowired
    private FlatOwnerService flatOwnerService;
    @PostMapping("/flights")
    public boolean addFlight(@RequestBody Flight flight) {
        return flightService.addFlight(flight);
    }
    @GetMapping("/flights")
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/flights/{id}")
    public Flight getFlightById(@PathVariable String id) {
        return flightService.getFlightById(id);
    }

    @PutMapping("/flights/{id}")
    public Flight updateFlight(@PathVariable String id, @RequestBody Flight flightDetails) {
        return flightService.editFlight(id, flightDetails);
    }

    @DeleteMapping("/flights/{id}")
    public boolean deleteFlight(@PathVariable String id) {
        return flightService.deleteFlight(id);
    }

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
    public Entertainment updateEntertainment(@PathVariable Integer id, @RequestBody Entertainment entertainmentDetails) {
        return entertainmentService.updateEntertainment(id, entertainmentDetails);
    }

    @DeleteMapping("/entertainment/{id}")
    public boolean deleteEntertainment(@PathVariable Integer id) {
        return entertainmentService.deleteEntertainment(id);
    }
    @GetMapping("/round-trip-flights")
    public List<RoundTripFlight> getAllRoundTripFlights() {
        return roundTripFlightService.getAllRoundTripFlights();
    }

    @PostMapping("/round-trip-flights")
    public boolean addRoundTripFlight(@RequestBody RoundTripFlight roundTripFlight) {
        return roundTripFlightService.addRoundTripFlight(roundTripFlight);
    }

    @GetMapping("/round-trip-flights/{id}")
    public RoundTripFlight getRoundTripFlightById(@PathVariable String id) {
        return roundTripFlightService.getRoundTripFlightById(id);
    }

    @PutMapping("/round-trip-flights/{id}")
    public RoundTripFlight updateRoundTripFlight(@PathVariable String id, @RequestBody RoundTripFlight roundTripFlightDetails) {
        return roundTripFlightService.editRoundTripFlight(id, roundTripFlightDetails);
    }

    @DeleteMapping("/round-trip-flights/{id}")
    public boolean deleteRoundTripFlight(@PathVariable String id) {
        return roundTripFlightService.deleteRoundTripFlight(id);
    }
    @GetMapping("/payments")
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/feedbacks")
    public List<Feedback> getAllFeedbacks() {
        return feedbackService.getAllFeedbacks();
    }

    @GetMapping("/feedbacks/{id}")
    public Feedback getFeedbackById(@PathVariable Integer id) {
        return feedbackService.getFeedbackById(id);
    }
    @GetMapping("/passengers")
    public List<Passenger> getAllPassengers() {
        return passengerService.getAllPassengers();
    }

    @GetMapping("/owners")
    public List<FlatOwner> getAllOwners() {
        return flatOwnerService.getAllOwners();
    }
    @GetMapping("/passengers/{id}")
    public Passenger getPassengerById(@PathVariable String id) {
        return passengerService.getPassengerById(id);
    }

    @GetMapping("/owners/{id}")
    public FlatOwner getOwnerById(@PathVariable String id) {
        return flatOwnerService.getOwnerById(id);
    }

    @DeleteMapping("/passengers/{id}")
    public boolean deletePassenger(@PathVariable String id) {
        return passengerService.deletePassenger(id);
    }

    @DeleteMapping("/owners/{id}")
    public boolean deleteOwner(@PathVariable String id) {
        return flatOwnerService.deleteOwner(id);
    }
    @PutMapping("/profile/{id}")
    public Account updateProfile(@PathVariable String id, @RequestBody Account account) {
        return accountService.updateAccount(id, account);
    }
    @GetMapping("/flights/count")
    public long countFlights() {
        return flightService.countFlights();
    }
    @GetMapping("/payments/total")
    public double sumTotalPayments() {
        return paymentService.sumTotalPayments();
    }
    @GetMapping("/feedback/count")
    public long countFeedback() {
        return feedbackService.countFeedbacks();
    }
    @GetMapping("/users")
    public List<Account> getAllUsers(){
        return accountService.getAllUsers();
    }
    @PutMapping("/accounts/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable("id") String oldAccountId, @RequestBody Account newAccountData) {
        Account updatedAccount = accountService.updateAccount(oldAccountId, newAccountData);
        return ResponseEntity.ok().body(updatedAccount);
    }
    @GetMapping("/accounts/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable("id") String accountId) {
        Account account = accountService.getAccountById(accountId);
        return ResponseEntity.ok().body(account);
    }
    @DeleteMapping("/users/delete/{id}")
    public boolean deleteAccount(@PathVariable String id){
        return accountService.deleteAccount(id);
    }

}

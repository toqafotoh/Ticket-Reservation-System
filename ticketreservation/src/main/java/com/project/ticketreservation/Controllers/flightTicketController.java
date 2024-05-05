package com.project.ticketreservation.Controllers;

import com.project.ticketreservation.utils.TicketQRcodeGenerator;
import com.google.zxing.WriterException;
import com.project.ticketreservation.Models.Flight;
import com.project.ticketreservation.Models.FlightTicket;
import com.project.ticketreservation.Models.Ticket;
import com.project.ticketreservation.Services.FlightService;
import com.project.ticketreservation.Services.FlightTicketServ;
import com.project.ticketreservation.Services.TicketService;
import com.project.ticketreservation.paypal.PaypalController;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ticket")
public class FlightTicketController {
    @Autowired
    private  FlightTicketServ fservice ;

    @Autowired
    private FlightService fs;

    @Autowired
    private TicketService ts ;


    @GetMapping("getTickets")
    public ResponseEntity<List<FlightTicket>> getTickets() throws IOException, WriterException {
        List<FlightTicket> tickets = fservice.getTickets();
        if (!tickets.isEmpty()){
            for (FlightTicket t : tickets){
                TicketQRcodeGenerator.generateQRcode(t);
            }
        }
        return ResponseEntity.ok(fservice.getTickets());
    }


    public String generate_QRCode(FlightTicket t) throws IOException, WriterException
    {
        String qrcode ;
        if (t != null)
            qrcode = TicketQRcodeGenerator.generateQRcode(t);
        else
            return "cannot generate qrcode";

        return qrcode;

    }


    @RequestMapping(
    value = "/add",  
    method = RequestMethod.POST)
    public void addTicket(@RequestBody Map<String, String> request) throws IOException, WriterException {
        FlightTicket t = new FlightTicket(request.get("firstName") ,
                request.get("lastName") , request.get("nationalID"), "GJ78" ,
                 request.get("flightNumber") , request.get("origin") , request.get("dest"));
        //Ticket ticket = new Ticket(null);         

        String qrcode = generate_QRCode(t);
        t.setQRcode(qrcode);
        fservice.addFlightTicket(t);
        //ts.addTicket(ticket);


    }

    @GetMapping("/{id}")
    public Flight findFlight(@PathVariable String id) {
        return fs.getFlightById(id); //new
    }

    @GetMapping("/t/{nationalID}")
    public Ticket getTicket(@PathVariable String nationalID) {
        return fservice.getTicket(nationalID);
    }

    @GetMapping("")
    public Flight findFlightparam(@RequestParam("flightNumber") String id) {
        return fs.getFlightById(id); //new
    }



    @GetMapping("/getFlights")
    public List<Flight> getFlights() {
        
        List<Flight> flights = fs.getAllFlights();
        return flights ; 
    }


    // @GetMapping("getpayid")
    // public String getpayid() {
    //     return PaypalController.payid ;
    // }
    
    @GetMapping("paymentTickets")
    public List<FlightTicket> getPayTickets() {
        return fservice.getPayTickets(PaypalController.payid);
    }
}
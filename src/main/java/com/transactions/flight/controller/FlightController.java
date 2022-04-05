package com.transactions.flight.controller;

import com.transactions.flight.dto.FlightBookingAcknowledgement;
import com.transactions.flight.dto.FlightBookingRequest;
import com.transactions.flight.service.FlightBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/flight")
@EnableTransactionManagement
public class FlightController {

    @Autowired
    private FlightBookingService service;

    @PostMapping("/book")
    public FlightBookingAcknowledgement bookFLightTicket(@RequestBody FlightBookingRequest request){
        return service.bookFlightTicket(request);
    }
}

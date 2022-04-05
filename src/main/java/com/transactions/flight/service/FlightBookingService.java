package com.transactions.flight.service;

import com.transactions.flight.dto.FlightBookingAcknowledgement;
import com.transactions.flight.dto.FlightBookingRequest;
import com.transactions.flight.entity.PassengerInfo;
import com.transactions.flight.entity.PaymentInfo;
import com.transactions.flight.repository.IPassengerRepository;
import com.transactions.flight.repository.IPaymentRepository;
import com.transactions.flight.util.PaymentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class FlightBookingService {

    @Autowired
    private IPassengerRepository passengerRepository;

    @Autowired
    private IPaymentRepository paymentRepository;

    public static final String SUCCESS = "SUCCESS";

    @Transactional//(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public FlightBookingAcknowledgement bookFlightTicket(FlightBookingRequest request){

        FlightBookingAcknowledgement acknowledgement = null;

        PaymentInfo paymentInfo = request.getPaymentInfo();
        PassengerInfo passengerInfo = request.getPassengerInfo();
        passengerInfo = passengerRepository.save(passengerInfo);

        /*
         * Validating Payments? proceed: throws Exception
         */
        PaymentUtils.validateCreditLimit(paymentInfo.getAccountNo(), passengerInfo.getFare());

        paymentInfo.setAmount(passengerInfo.getFare());
        paymentInfo.setPassengerId(passengerInfo.getPid());

        paymentRepository.save(paymentInfo);

        return  new FlightBookingAcknowledgement(SUCCESS, passengerInfo.getFare()
                , UUID.randomUUID().toString().split("-")[0],passengerInfo);
    }
}

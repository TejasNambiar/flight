package com.transactions.flight.util;

import com.transactions.flight.exception.InsufficientAmountException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@UtilityClass
@Slf4j
public class PaymentUtils {
    private static final Map<String, Double> paymentMap = new HashMap<>();

    static {
        paymentMap.put("acc1", 12000.0);
        paymentMap.put("acc2", 10000.0);
        paymentMap.put("acc3", 5000.0);
        paymentMap.put("acc4", 8000.0);
    }

    public static boolean validateCreditLimit(String accNo, double paidAmount){
        System.out.println("accountNo:: "+accNo);
        System.out.println("paidAmount:: "+paidAmount);
        if(paidAmount>paymentMap.get(accNo)){
            throw new InsufficientAmountException("Insufficient Funds!");
        }
        return true;
    }
}

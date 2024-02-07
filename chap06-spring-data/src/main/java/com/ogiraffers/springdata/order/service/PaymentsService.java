package com.ogiraffers.springdata.order.service;

import com.ogiraffers.springdata.order.entity.Payments;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PaymentsService {

    public Payments orderPayments(int value){
        Payments payments = new Payments();
        payments.setPaymentsDate(new Date());
        return payments;

    }
}

package com.altrocks.river.utils;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.altrocks.river.hdfc.outgoingpayment.OutgoingPaymentService;
import com.mashape.unirest.http.exceptions.UnirestException;

@Component
public class Schedular {
	@Autowired
	OutgoingPaymentService outgoingPaymentService;

	@Scheduled(fixedRate = 9000)
	public void schedulaerun() throws UnirestException, IOException {
//		System.err.println("8888");
		outgoingPaymentService.saveOutgoingPayments();
		
		
		
	}
}

package com.example.order.rest;

import com.example.order.dto.PaymentDto;
import com.example.order.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PaymentDto processPayment(@RequestBody PaymentDto paymentDto) {
        paymentService.processPayment(paymentDto);
        return paymentDto;
    }
}

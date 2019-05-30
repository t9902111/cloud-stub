package com.example.order.service;

import com.example.order.dto.PaymentDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class PaymentService {
    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);
    private static final String TOPIC = "payment";

    @Autowired
    @Qualifier("paymentKafkaTemplate")
    private KafkaTemplate<String, PaymentDto> kafkaTemplate;

    public void processPayment(PaymentDto paymentDto) {
        logger.info(String.format("$$ -> Producing payment --> %s", paymentDto.toString()));
        ListenableFuture<SendResult<String, PaymentDto>> future = kafkaTemplate.send(TOPIC, paymentDto);

        future.addCallback(new ListenableFutureCallback<SendResult<String, PaymentDto>>() {
            @Override
            public void onSuccess(SendResult<String, PaymentDto> result) {
                System.out.println("Sent message=[" + paymentDto.toString() +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=["
                        + paymentDto.toString() + "] due to : " + ex.getMessage());
            }
        });
    }
}

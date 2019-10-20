package com.kafka.events.integration.kafka.listener;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.kafka.events.integration.repository.CustomerRepository;
import com.kafka.events.integration.resource.ChargeAccountResource;

@Component
public class CustomerAccountChargedEventListener {
	
	private static final Logger LOG = LoggerFactory.getLogger(CustomerAccountChargedEventListener.class);
	
	@Autowired
    private CustomerRepository customerRepository;

    @KafkaListener(topics = "customer.account.charged")
    @Transactional
    public void chargeAccountEvent(
            @Payload(required = false) ChargeAccountResource value,
            @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key
    ) {
    	LOG.info("User: {} has charged his account: {}", key, value);
        customerRepository.updateBalance(key, value.getAmount());
    }
}

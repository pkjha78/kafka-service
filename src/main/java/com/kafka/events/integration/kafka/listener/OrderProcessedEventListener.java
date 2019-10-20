package com.kafka.events.integration.kafka.listener;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.kafka.events.integration.repository.CustomerRepository;
import com.kafka.events.integration.resource.OrderProcessedResource;
import com.kafka.events.integration.resource.PaymentOperationStatus;
import com.kafka.events.integration.resource.PaymentProcessedResource;

@Component
public class OrderProcessedEventListener {
	
	private static final Logger LOG = LoggerFactory.getLogger(OrderProcessedEventListener.class);

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	@Qualifier("withdrawStatusKafkaTemplate")
	private KafkaTemplate<String, PaymentProcessedResource> kafkaTemplate;

	@KafkaListener(topics = "order.processed")
	@Transactional
	public void orderProcessedEvent(@Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
			@Payload OrderProcessedResource value) {
		LOG.info("Withdrawing from user's account: {}", value);

		boolean result = customerRepository.withdrawAmount(value.getLogin(), value.getPrice()) > 0;

		PaymentProcessedResource resource = new PaymentProcessedResource(value.getLogin(),
				PaymentOperationStatus.fromResult(result));

		kafkaTemplate.send("order.payment.processed", key, resource);
	}
}

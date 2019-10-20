package com.kafka.events.integration.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.events.integration.repository.CustomerRepository;
import com.kafka.events.integration.resource.ChargeAccountResource;
import com.kafka.events.integration.resource.CustomerResource;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	
	private static final Logger LOG = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	@Qualifier("kafkaTemplate")
	private KafkaTemplate<String, ChargeAccountResource> template;

	@Autowired
	private CustomerRepository customerRepository;

	@RequestMapping(value = "/{login}", method = RequestMethod.POST)
	public void chargeAccount(@PathVariable("login") String login, @RequestBody ChargeAccountResource resource) {
		LOG.info("Creating user:{} recharge event: {}", login, resource);

		template.send("customer.account.charged", login, resource);
	}

	@RequestMapping(value = "/{login}", method = RequestMethod.GET)
	public CustomerResource customerInfo(@PathVariable("login") String login) {
		return customerRepository.findByLogin(login).map(c -> new CustomerResource(c.getLogin(), c.getAmount())).get();
	}
}

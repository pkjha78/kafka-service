package com.kafka.events.integration.resource;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ChargeAccountResource {
	private BigDecimal amount;
}

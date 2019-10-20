package com.kafka.events.integration.resource;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProcessedResource {

	private String login;
	private String uid;
	private BigDecimal price;
	private List<OrderProductResource> products;

}

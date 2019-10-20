package com.kafka.events.integration.resource;

import lombok.Data;

@Data
public class OrderProductResource {
	private String name;
    private Integer count;
}
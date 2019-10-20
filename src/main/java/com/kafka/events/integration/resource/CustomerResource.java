package com.kafka.events.integration.resource;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerResource {

    private String login;

    private BigDecimal amount;

}

package com.kafka.events.integration.kafka.serializers;

import com.kafka.events.integration.kafka.BaseJacksonSerializer;
import com.kafka.events.integration.resource.PaymentProcessedResource;

public class WithdrawStatusJacksonSerializer extends BaseJacksonSerializer<PaymentProcessedResource> {

	@Override
	public Class<PaymentProcessedResource> getClz() {
		return PaymentProcessedResource.class;
	}
}
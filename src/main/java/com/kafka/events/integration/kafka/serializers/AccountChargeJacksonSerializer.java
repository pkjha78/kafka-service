package com.kafka.events.integration.kafka.serializers;

import com.kafka.events.integration.kafka.BaseJacksonSerializer;
import com.kafka.events.integration.resource.ChargeAccountResource;

public class AccountChargeJacksonSerializer extends BaseJacksonSerializer<ChargeAccountResource> {

	@Override
	public Class<ChargeAccountResource> getClz() {
		return ChargeAccountResource.class;
	}
}
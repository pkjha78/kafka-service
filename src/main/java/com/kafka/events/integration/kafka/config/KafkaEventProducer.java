package com.kafka.events.integration.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.kafka.events.integration.resource.ChargeAccountResource;
import com.kafka.events.integration.resource.PaymentProcessedResource;

@Configuration
public class KafkaEventProducer {

	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;
	
	@Bean
	public ProducerFactory<String, ChargeAccountResource> producerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put("bootstrap.servers", bootstrapServers);
		props.put("key.serializer", StringSerializer.class);
		props.put("value.serializer", "com.kafka.events.integration.kafka.serializers.AccountChargeJacksonSerializer");

		return new DefaultKafkaProducerFactory<>(props);
	}

	@Bean
	public ProducerFactory<String, PaymentProcessedResource> withdrawStatusProducerFactory() {
		Map<String, Object> props =  new HashMap<>();
		props.put("bootstrap.servers", bootstrapServers);
		props.put("key.serializer", StringSerializer.class);
		props.put("value.serializer", "com.kafka.events.integration.kafka.serializers.WithdrawStatusJacksonSerializer");

		return new DefaultKafkaProducerFactory<>(props);
	}

	@Bean
	public Map<String, Object> producerConfigs() {
		Map<String, Object> props = new HashMap<>();

		props.put("bootstrap.servers", bootstrapServers);

		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);

		props.put("key.serializer", StringSerializer.class);

		return props;
	}

	@Bean(name = "kafkaTemplate")
	public KafkaTemplate<String, ChargeAccountResource> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}

	@Bean(name = "withdrawStatusKafkaTemplate")
	public KafkaTemplate<String, PaymentProcessedResource> withdrawStatusKafkaTemplate() {
		return new KafkaTemplate<>(withdrawStatusProducerFactory());
	}
}

package com.kafka.events.integration.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

@Configuration
public class KafkaEventConsumer {
	
	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;

	@Bean
	ConcurrentKafkaListenerContainerFactory<Integer, String> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<Integer, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		Map<String, Object> props = new HashMap<>();
		props.put("bootstrap.servers", bootstrapServers);
		props.put("group.id", "customerServiceGroup");
		props.put("key.deserializer", StringDeserializer.class);
		props.put("value.deserializer", StringDeserializer.class);
		factory.setConsumerFactory(consumerFactory());
		factory.setMessageConverter(new StringJsonMessageConverter());
		return factory;
	}

	@Bean
	public ConsumerFactory<Integer, String> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(consumerConfigs());
	}

	@Bean
	public Map<String, Object> consumerConfigs() {
		Map<String, Object> props = new HashMap<>();
		props.put("bootstrap.servers", bootstrapServers);
		props.put("group.id", "customerServiceGroup");
		props.put("key.deserializer", StringDeserializer.class);
		props.put("value.deserializer", StringDeserializer.class);

		return props;
	}
}
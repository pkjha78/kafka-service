package com.kafka.events.integration.kafka.config;

import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.stereotype.Component;
/**
 * Topic(s) [customer.account.charged] is/are not present and missingTopicsFatal is false in that case
 * if this is true application don't run. Workaround for missing topics on kafka
 * 
 * @author spart
 *
 */
@Component
public class ContainerFactory {
	ContainerFactory(ConcurrentKafkaListenerContainerFactory<?, ?> factory) {
        factory.getContainerProperties().setMissingTopicsFatal(false);
    }
}

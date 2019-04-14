package com.jene.newsletter.microservices.subscription.kafka;

import com.jene.newsletter.microservices.subscription.model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventPublisher {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private KafkaTemplate<String, Event> kafkaTemplate;

    public void publish(final Event event) {

		logger.debug("Publishing event {} to subscription queue", event.getId());
		kafkaTemplate.send(KafkaConfiguration.DATABASE, event.getId(), event);
    }

}

package com.jene.newsletter.microservices.subscription.kafka;

import com.jene.newsletter.microservices.subscription.model.SubscriptionEvent;
import com.jene.newsletter.microservices.subscription.service.SubscriptionsDb;
import com.jene.newsletter.microservices.subscription.service.SubscriptionsService;
import com.jene.newsletter.microservices.subscription.service.SubscriptionsTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SubscriptionsKafkaListener {

	private final Logger logger = LoggerFactory.getLogger(SubscriptionsKafkaListener.class);

	@Autowired
	private SubscriptionsDb subscriptionsDb;

    @Autowired
    private SubscriptionsService subscriptionsService;


	private SubscriptionEvent event = null;


	/**
     * receive request as key, value (id, json_req)
     * @param id
     * @param s
     * @param acknowledgment
     * @throws IOException
     */
	@KafkaListener(topics = KafkaConfiguration.SUBSCRIPTIONS, containerFactory = "kafkaListenerContainerFactoryConsumer")
	public void newSubscriptions(String id, SubscriptionEvent s, Acknowledgment acknowledgment) throws IOException {

		logger.info("Revceived subscription " + id);
		// save to database with new subscriptionId created
		SubscriptionEvent e = subscriptionsDb.store(s);
		// for test purpouse
		this.event = s;
        // add events to topic 'database'
        subscriptionsService.addResponse(s);
		acknowledgment.acknowledge();
	}

	//added for testing purpose
	public SubscriptionEvent message(){return this.event;}
}

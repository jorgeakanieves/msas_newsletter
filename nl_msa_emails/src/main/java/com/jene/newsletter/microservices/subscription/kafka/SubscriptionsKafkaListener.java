package com.jene.newsletter.microservices.subscription.kafka;

import com.jene.newsletter.microservices.subscription.model.SubscriptionEvent;
import com.jene.newsletter.microservices.subscription.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.IOException;

@Component
public class SubscriptionsKafkaListener {

	private final Logger logger = LoggerFactory.getLogger(SubscriptionsKafkaListener.class);

	@Autowired
	private EmailService subscriptionsEmail;

	private SubscriptionEvent event = null;

	/**
	 * receive request as key, value (id, json_req)
	 * @param id
	 * @param s
	 * @param acknowledgment
	 * @throws AddressException
	 * @throws MessagingException
	 * @throws IOException
	 */
	@KafkaListener(topics = KafkaConfiguration.DATABASE, containerFactory = "kafkaListenerContainerFactoryConsumer")
	public void newDbSubscriptions(String id, SubscriptionEvent s, Acknowledgment acknowledgment) throws AddressException, MessagingException, IOException {
		logger.info("Revceived subscription " + id);
		// for test purpouse
		this.event = s;
		// send email to user
		subscriptionsEmail.sendEmail(s);
		acknowledgment.acknowledge();
	}

	//added for testing purpose
	public SubscriptionEvent message(){return this.event;}
}

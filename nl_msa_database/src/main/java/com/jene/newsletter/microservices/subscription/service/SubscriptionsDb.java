package com.jene.newsletter.microservices.subscription.service;

import com.jene.newsletter.microservices.subscription.model.SubscriptionEvent;
import com.jene.newsletter.microservices.subscription.repository.SubscriptionsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SubscriptionsDb {

	private final Logger logger = LoggerFactory.getLogger(SubscriptionsDb.class);

	@Autowired
	private SubscriptionsRepository subscriptionsRepository;

	public SubscriptionEvent store(SubscriptionEvent s) {
		// TODO: check if email exists before
		SubscriptionEvent e = subscriptionsRepository.save(s);
		return e;
	}

}

package com.jene.newsletter.microservices.subscription.service;

import com.jene.newsletter.microservices.subscription.kafka.EventPublisher;
import com.jene.newsletter.microservices.subscription.model.SubscriptionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SubscriptionsTopic implements SubscriptionsService {

  @Autowired
  private EventPublisher eventsPublisher;

  @Override
  public SubscriptionEvent addResponse(SubscriptionEvent subscriptionEvent) {

    eventsPublisher.publish(subscriptionEvent);
    return subscriptionEvent;
  }
}

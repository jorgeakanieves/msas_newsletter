package com.jene.newsletter.microservices.subscription.service;

import com.jene.newsletter.microservices.subscription.model.SubscriptionEvent;

public interface SubscriptionsService {

  SubscriptionEvent addResponse(SubscriptionEvent subscriptionEvent);
  
}

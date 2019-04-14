package com.jene.newsletter.microservices.subscription.service;

import com.jene.newsletter.microservices.subscription.model.SubscriptionEvent;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.IOException;

public interface EmailService {

  void sendEmail(SubscriptionEvent subscriptionEvent)  throws AddressException, MessagingException, IOException;
  
}

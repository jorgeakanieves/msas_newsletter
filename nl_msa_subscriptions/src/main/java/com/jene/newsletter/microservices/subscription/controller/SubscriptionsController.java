package com.jene.newsletter.microservices.subscription.controller;

import javax.validation.Valid;

import com.jene.newsletter.microservices.subscription.model.SubscriptionEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.jene.newsletter.microservices.subscription.service.SubscriptionsService;

import java.util.UUID;

@RestController
@RequestMapping(value = "/subscriptions", produces = MediaType.APPLICATION_JSON_VALUE)
public class SubscriptionsController {

  private static final Logger logger = LoggerFactory.getLogger(SubscriptionsController.class);

  @Autowired
  private SubscriptionsService service;

  /**
   * Controller for newsletter reqs
   * @param subscriptionEvent
   * @return
   */
  @PostMapping(value="/new", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<SubscriptionEvent> addResponse(@RequestBody(required = true) @Valid SubscriptionEvent subscriptionEvent) {
    subscriptionEvent.setId(UUID.randomUUID().toString());
    logger.info("New subscriptionEvent '{}'", subscriptionEvent.getId());
    return new ResponseEntity<SubscriptionEvent>(service.addResponse(subscriptionEvent), HttpStatus.OK);
  }
}

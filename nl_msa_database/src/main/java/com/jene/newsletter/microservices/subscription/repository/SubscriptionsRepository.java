package com.jene.newsletter.microservices.subscription.repository;

import com.jene.newsletter.microservices.subscription.model.SubscriptionEvent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SubscriptionsRepository extends CrudRepository<SubscriptionEvent, Long> {

}

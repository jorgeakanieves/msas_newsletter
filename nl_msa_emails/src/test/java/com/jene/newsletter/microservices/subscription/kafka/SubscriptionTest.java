package com.jene.newsletter.microservices.subscription.kafka;

import com.jene.newsletter.microservices.subscription.model.Event;
import com.jene.newsletter.microservices.subscription.model.SubscriptionEvent;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
@ActiveProfiles("test")
public class SubscriptionTest {

    @Value("${kafka.topic.simpleMessageTopic}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, Event> kafkaTemplate;

    @Autowired
    private SubscriptionsKafkaListener consumer;

    @ClassRule
    public static KafkaEmbedded kafkaEmbedded = new KafkaEmbedded(1,false, KafkaConfiguration.DATABASE);

    @Test
    public void testSendReceive() throws Exception {
        SubscriptionEvent s = new SubscriptionEvent();
        s.setId("1");
        s.setSubscriptionId("11");
        s.setEmail("test@test.com");
        kafkaTemplate.send(KafkaConfiguration.DATABASE, s.getId(), s);
        TimeUnit.SECONDS.sleep(1);
        Assert.assertEquals(s, consumer.message());
    }
}
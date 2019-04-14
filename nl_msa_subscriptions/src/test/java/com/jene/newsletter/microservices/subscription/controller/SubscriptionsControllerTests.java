package com.jene.newsletter.microservices.subscription.controller;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import com.jene.newsletter.microservices.subscription.service.SubscriptionsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.jene.newsletter.microservices.subscription.model.SubscriptionEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.ResultActions;


@RunWith(SpringRunner.class)
@WebMvcTest(SubscriptionsController.class)
public class SubscriptionsControllerTests {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private SubscriptionsService service;

  @Before
  public void setup() throws Exception {
  }

  @Test
  public void testAddResponse() throws Exception {
    ResultActions ra = mvc.perform(post("/subscriptions/new").contentType(MediaType.APPLICATION_JSON)
            .content("{\"email\": \"test@test.com\",\"firstName\": \"Jorge\",\"gender\":\"Male\",\"dateOfBith\":\"12-12-2012\",\"flagOfconsent\":\"true\", \"newsletterId\":\"61261-126211-162161\"}"));

    checkResponseIsCompleted(ra, "test@test.com", "Jorge");
  }

  private void checkResponseIsCompleted(ResultActions ra, String email, String firstName) throws Exception {
    ra.andExpect(status().isOk())
            .andExpect(jsonPath("$.email", is(email)))
            .andExpect(jsonPath("$.firstName", is(firstName)));

  }
}

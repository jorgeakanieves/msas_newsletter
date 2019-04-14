package com.jene.newsletter.microservices.subscription.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import org.springframework.data.annotation.Id;

public final class SubscriptionEvent implements Event {

  public SubscriptionEvent() {
  }

  private String Id;

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private String subscriptionId;

  private String newsletterId;

  @NotNull(message = "email can not be null")
  private String email;

  private String firstName;

  private String gender;

  @NotNull(message = "dateOfBirth can not be null")
  private Date dateOfBirth;

  @NotNull(message = "flagOfConsent can not be null")
  private Boolean flagOfConsent;

  public String getId() {
    return Id;
  }

  public void setId(String id) {
    Id = id;
  }

  public String getSubscriptionId() {
    return subscriptionId;
  }

  public void setSubscriptionId(String subscriptionId) {
    this.subscriptionId = subscriptionId;
  }

  public String getNewsletterId() {
    return newsletterId;
  }

  public void setNewsletterId(String newsletterId) {
    this.newsletterId = newsletterId;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public Boolean getFlagOfConsent() {
    return flagOfConsent;
  }

  public void setFlagOfConsent(Boolean flagOfConsent) {
    this.flagOfConsent = flagOfConsent;
  }

  @Override
  public String toString() {
    return "SubscriptionEvent{" +
            "Id='" + Id + '\'' +
            ", subscriptionId='" + subscriptionId + '\'' +
            ", newsletterId='" + newsletterId + '\'' +
            ", email='" + email + '\'' +
            ", firstName='" + firstName + '\'' +
            ", gender='" + gender + '\'' +
            ", dateOfBirth=" + dateOfBirth +
            ", flagOfConsent=" + flagOfConsent +
            '}';
  }
}

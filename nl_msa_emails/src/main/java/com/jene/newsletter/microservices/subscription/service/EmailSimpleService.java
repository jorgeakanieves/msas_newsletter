package com.jene.newsletter.microservices.subscription.service;

import com.jene.newsletter.microservices.subscription.model.SubscriptionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;


@Service
public class EmailSimpleService implements EmailService {

  @Value("${spring.mail.properties.mail.smtp.auth}")
  private String auth;
  @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
  private String tls;
  @Value("${spring.mail.host}")
  private String host;
  @Value("${spring.mail.port}")
  private String port;
  @Value("${spring.mail.port}")
  private String username;
  @Value("${spring.mail.port}")
  private String pass;


  @Override
  public void sendEmail(SubscriptionEvent subscriptionEvent) throws AddressException, MessagingException, IOException {

    Properties props = new Properties();
    props.put("mail.smtp.auth", auth);
    props.put("mail.smtp.starttls.enable", tls);
    props.put("mail.smtp.host", host);
    props.put("mail.smtp.port", port);

    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, pass);
      }
    });
    Message msg = new MimeMessage(session);
    msg.setFrom(new InternetAddress(username, false));

    msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(subscriptionEvent.getEmail()));
    String content = "Thanks for register, your account id is: " + subscriptionEvent.getSubscriptionId();
    msg.setSubject("Newsletter subscription");
    msg.setContent(content, "text/html");
    msg.setSentDate(new Date());

    MimeBodyPart messageBodyPart = new MimeBodyPart();
    messageBodyPart.setContent(content, "text/html");

    Multipart multipart = new MimeMultipart();
    multipart.addBodyPart(messageBodyPart);
    msg.setContent(multipart);
    Transport.send(msg);
  }
}
